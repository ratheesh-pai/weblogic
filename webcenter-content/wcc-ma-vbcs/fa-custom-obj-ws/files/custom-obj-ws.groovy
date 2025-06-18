// Verify if user has access to FA object
if (boType == "Invoice") {
  String InvoiceNumber = boValue1;
  adf.util.InvoiceValidate(InvoiceNumber);
} else {
  String msg = "Error - Unsupported boType - " + boType;
  println(msg);
  return msg;
}


// validate appName
if (appName == null || appName.trim().length() == 0) {
  String msg = "Error - Mandatory parameter not available - appName";
  println(msg);
  return msg;
}

// validate boType
if (boType == null || boType.trim().length() == 0) {
  String msg = "Error - Mandatory parameter not available - boType";
  println(msg);
  return msg;
}

// validate that boKey1 and boValue1 are available
if (boKey1 == null || boKey1.trim().length() == 0) {
  String msg = "Error - Mandatory parameter not available - boKey1";
  println(msg);
  return msg;
}

if (boValue1 == null || boValue1.trim().length() == 0) {
  String msg = "Error - Mandatory parameter not available - boValue1";
  println(msg);
  return msg;
}

// Build request
def curUserName = adf.context.getSecurityContext().getUserName();

def request =
  [
    commandNamespace : "UCM_Managed_Attachments",
    solutionNamespace: "UCM_Managed_Attachments",
    username         : curUserName,
    userContext      : [],
    requestParameters:
      [
        entry:
          [[
             key  : "application",
             value: appName,
           ],
           [
             key  : "businessObjectType",
             value: boType,
           ]]
      ]
  ];

// add key/value pairs to "entry"
List<Map> entries = (List<Map>) request["requestParameters"]["entry"];
def boKeys = [boKey1, boKey2, boKey3, boKey4, boKey5];
def boValues = [boValue1, boValue2, boValue3, boValue4, boValue5];

for (int i = 0; i < 5; i++) {
  String boKey = boKeys.get(i);
  boKey = boKey == null ? "" : boKey.trim();

  String boValue = boValues.get(i);
  boValue = boValue == null ? "" : boValue.trim();

  def keyIndex = i + 1;
  // validate that both key and value are present
  if (boKey.length() > 0 && boValue.length() == 0) {
    def msg = "Error - For key boKey" + keyIndex + ', Value not available for boValue' + keyIndex;
    return msg;
  }

  if (boKey.length() == 0 && boValue.length() > 0) {
    def msg = "Error - For value boValue" + keyIndex + ', Key not available for boKey' + keyIndex;
    return msg;
  }

  // add key, for example: [ key  : "businessObjectKey1", value: "InvoiceNumber" ],
  if (boKey.length() > 0) {
    def boKeyName = "businessObjectKey" + keyIndex;
    entries.add(["key": boKeyName, "value": boKey]);
  }

  // add value, for example: [ key  : "businessObjectValue1", value: "100000" ],
  if (boValue.length() > 0) {
    def boValueName = "businessObjectValue" + keyIndex;
    entries.add(["key": boValueName, "value": boValue]);
  }
}


// Get Managed attachment guid
try {
  // Execute UCM Grant webservice
  def response = adf.webServices.WebCenterContentGrantWebservice.execute(request);
  println("Grant access ws response - " + response);

  // Retrieve UCM MA url from response
  String ucmMaUrl = "";
  String afGuidVal = "";

  def responseCommands = response["responseCommands"].get(0);
  for (responseCommand in responseCommands) {
    if (responseCommand["command"] == "OPEN_BROWSER") {
      String url = responseCommand["value"];

      // Extract afGuid
      url = url.substring(url.indexOf("?") + 1);
      String[] urlParams = url.split('&');
      for (String urlParam : urlParams) {
        String[] nameValPair = urlParam.split('=');
        if (nameValPair[0] == "afGuid") {
          afGuidVal = nameValPair[1];
          break;
        }
      }
      ucmMaUrl = "&afGuid=" + afGuidVal;

      println("Extracted ucm attachment url suffix" + ucmMaUrl);
      break;
    }
  }

  return ucmMaUrl;
} catch (Exception e) {
  def msg = "Error getting UCM Managed attachment url for " + boType + " " + e;
  println(msg);

  return msg;
}

// If no error and not able to get url
return "Error - No attachment url in response";

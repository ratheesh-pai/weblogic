println("Executing WebCenter Content Grant Access for SR " + SrNumber);

def curUserName = adf.context.getSecurityContext().getUserName();
def locale = adf.context.getLocale();

def response = [:];
// NOTE - Change this to a name corresponding to your instance
def applicationName = "MyCompanySalesCloud";

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
             key  : "businessObjectType",
             value: "ServiceRequest",
           ],
           [
             key  : "businessObjectKey1",
             value: "SRNumber",
           ],
           [
             key  : "businessObjectValue1",
             value: SrNumber,
           ],
           [
             key  : "application",
             value: applicationName,
           ],
           [
             key  : "labelValue1",
             value: "Managed Attachments - Service Request Documents",
           ]]
      ]
  ];


try {
  // Execute WebCenter Content Grant web service
  response = adf.webServices.WebCenterContentGrantWebservice.execute(request);

  // Retrieve WebCenter Content MA URL from response
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
      ucmMaUrl = "&afGuid=" + afGuidVal + "&locale=" + locale;

      println("Extracted WebCenter Content attachment url suffix " + ucmMaUrl);
      break;
    }
  }

  return ucmMaUrl;
} catch (Exception e) {
  println("Error getting WebCenter Content Managed attachment url for SR " + SrNumber + " " + e);
}
 // NOTE - Before final publishing comment out console.log debug statements if planning to
 // deploy on production environments

$variables.statusMessage = "Loading Attachments";

// Create payload
const requestBody = {
  appName: $variables.appName,
  boType: $variables.boType,
  boKey1: $variables.boKey1,
  boValue1: $variables.boValue1
};

// Add boKey2/boValue2
if ($variables.boKey2 !== null) {
  requestBody.boKey2 = $variables.boKey2;
}

if ($variables.boValue2 !== null) {
  requestBody.boValue2 = $variables.boValue2;
}

// Add boKey3/boValue3
if ($variables.boKey3 !== null) {
  requestBody.boKey3 = $variables.boKey3;
}

if ($variables.boValue3 !== null) {
  requestBody.boValue3 = $variables.boValue3;
}

// Add boKey4/boValue4
if ($variables.boKey4 !== null) {
  requestBody.boKey4 = $variables.boKey4;
}

if ($variables.boValue4 !== null) {
  requestBody.boValue4 = $variables.boValue4;
}

// Add boKey5/boValue5
if ($variables.boKey5 !== null) {
  requestBody.boKey5 = $variables.boKey5;
}

if ($variables.boValue5 !== null) {
  requestBody.boValue5 = $variables.boValue5;
}

console.log("Payload to Managed attachment api " + JSON.stringify(requestBody));

// Make REST API call to custom object managed attachment function
const response = await Actions.callRest(context, {
  endpoint: '<Service Endpoint>',
  body: requestBody,
  responseBodyFormat: 'json'
}, { id: 'GetWccMaDocsUrl' });

console.log("Got FA Rest response " + JSON.stringify(response));

const managedAttachmentUrl = "https://{WCCHost}:{WCCPort}/cs/idcplg?IdcService=EMBEDLOGIN_HOME&ActAsAnonymous=true";
console.log("Response url - " + response?.body?.result);

// If response has afGuid redirect to managed attachment url
if (response?.body?.result?.startsWith("&afGuid=")) {
  let redirectUrl = managedAttachmentUrl + response?.body?.result;

  console.log("Redirecting to - " + redirectUrl);

  await Actions.openUrl(context, {
	history: 'replace',
	url: redirectUrl
  });

} else {
  console.log("Error in loading Attachments. Please contact Administrator");

  $variables.statusMessage = "Error in loading Attachments. Please contact Administrator";
}

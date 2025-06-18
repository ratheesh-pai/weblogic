// validate InvoiceNumber is not empty
InvoiceNumber = InvoiceNumber == null ? "" : InvoiceNumber.trim();
if (InvoiceNumber.length() == 0) {
  String msg = "Error - InvoiceNumber is empty";

  println(msg);
  throw new oracle.jbo.ValidationException(msg);
}

def invoicesMap = null;

// Verify if user has access to invoice object
try {
  def invoiceWs = adf.webServices.InvoiceWebservice;
  invoiceWs.requestHTTPHeaders = ['Content-Type': 'application/json'];
  invoicesMap = invoiceWs.GET(InvoiceNumber);
} catch (Exception e) {
  String msg = "Error accessing Invoice object for - " + InvoiceNumber + " " + e;

  println(msg);
  throw new oracle.jbo.ValidationException(msg);
}


// validate only one unique invoice is returned.
String countStr = invoicesMap["count"];
int count = new Integer(countStr);
if (count == 0) {
  String msg = "Error in invoice access - No invoice found with InvoiceNumber " + InvoiceNumber;

  println(msg);
  throw new oracle.jbo.ValidationException(msg);
} else if (count > 1) {
  String msg = "Error - Multiple invoices found with InvoiceNumber " + InvoiceNumber + ". Need unique key for managed attachments";

  println(msg);
  throw new oracle.jbo.ValidationException(msg);
} else if (count != 1) {
  String msg = "Error in invoice access for InvoiceNumber - " + InvoiceNumber + ". Found invalid number of objects " + count;

  println(msg);
  throw new oracle.jbo.ValidationException(msg);
}

// Validate that api has returned correct object
def retInvoiceObj = invoicesMap["items"].get(0);
String retInvoiceNumber = retInvoiceObj["InvoiceNumber"];
if (!(retInvoiceNumber == InvoiceNumber)){
  String msg = "Error in Invoice access - Invalid invoice found with InvoiceNumber " + InvoiceNumber;

  println(msg);
  throw new oracle.jbo.ValidationException(msg);
}


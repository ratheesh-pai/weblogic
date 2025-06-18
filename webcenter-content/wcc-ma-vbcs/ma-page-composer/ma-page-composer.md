# Use Page Composer to Insert Managed Attachments Link in Fusion Applications Page

## Introduction

In this lab, we will use page composer to insert managed attachments Link in Fusion Applications page of **Payables**, **Invoices** module

**Estimated Lab Time**: *10 minutes*

### Objectives

In this lab, you will

- Use page composer to insert managed attachments Link in Fusion Applications page of **Payables**, **Invoices** module

### Prerequisites

This lab assumes you have:

- A Paid or LiveLabs Oracle Cloud account
- Access to Oracle Fusion Apps

## Task 1: Use Page Composer to Insert Managed Attachments Link in Fusion Applications Page

1. Navigate to **Payables** and then **Invoices**.

2. Search and open an invoice record.

3. Select **Tools** and then **Page Composer**.

4. Click **Structure**.

5. Select the structure pane (which is available by default at the bottom of the page).

6. In the structure pane select the UI element on which you need to insert the hyperlink. Click the **+** button in the panel.

7. In the **Add Content** pop-up, select **Components**.

8. Select **Hyperlink** and click **+Add**.

9. In the **structure** pane, select the hyperlink you just added or in the **Select** tab, select the hyperlink component in the UI and click **Edit Component**.

10. Enter

    a. Destination

    ```text
    <copy>
    https://{FAHost}/fscmUI/redwood/WccManagedAttachmentUI?vbdt%3ApreferExtensionVersion={version}&appName={appName}&boType=Invoice&boKey1=InvoiceNumber&boValue1=#{bindings.InvoiceNum.inputValue}
    </copy>
    ```

    The URL is from the Lab - `Create VBCS-Based Application Extension`. Replace values for **{version}**, **{appName}** as applicable. The parameter **boValue1** is set as a bindings parameter value of invoice number of the current invoice.

    b. Short Desc - **Documents**

    c. Target Frame - **_blank**

    d. Text - **Documents**

11. Click **OK**.

12. Close the Page Composer by clicking **Close**.

## Acknowledgements

- **Authors-** Ratheesh Pai, Senior Principal Member Technical Staff, Oracle WebCenter Content
- **Contributors-** Ratheesh Pai, Rajiv Malhotra, Vinay Kumar
- **Last Updated By/Date-** Ratheesh Pai, June 2025

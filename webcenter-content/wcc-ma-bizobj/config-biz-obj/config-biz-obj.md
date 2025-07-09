# Extend Business Object To Get Managed Attachments Page And Embed In Module 

## Introduction

In this lab we will extend business object to call Grant WebService, get managed attachments page and embed in a module

**Estimated Lab Time**: *45 minutes*

### Objectives

In this lab, you will

- Create a Global Function to Call WebCenter Content Grant WebService
- Create a Mashup To Embed Managed Attachments Page
- Extend Specific Business Object To Show Managed Attachments Page

### Prerequisites

This lab assumes you have:

- A Paid or LiveLabs Oracle Cloud account
- Access to an Oracle Fusion Applications instance
- All previous labs successfully completed

## Task 1: Create a Global Function to Call WebCenter Content Grant WebService

1. Choose **Common Setup** and then **Global Functions** from the left navigation menu.

2. On the Global Functions page, click the **Add a Global Function** icon.

3. On the Create Global Function page in the **Function Name** field, specify a name for eg  **WebCenterContentGrantWebserviceFunction**.

4. Select **String** from the **Returns** drop-down menu.

5. In the Parameters section, click the **Add Parameter** icon and add the following fields:

        a. Name - SrNumber

        b. Type - String

6. Download the [Grant Webservice](files/grant-ws.groovy) groovy code.

7. In the **Edit Script** field, paste the contents of the downloaded script:

    **Note**: For `applicationName`, enter a name for your Sales cloud instance. For example, **\<MyCompany\>SalesCloud**.

    **About Grant webservice payload**

    You can specify up to 5 keys (businessObjectKey1,. businessObjectKey5) and their corresponding values (businessObjectValue1 ... businessObjectValue5) respectively in the payload. The maximum character lengths of various parameters are:

    - applicationName: 20
    - businessObjectType: 100
    - businessObjectKey's: 80
    - businessObjectValue's: 80

    These variables should form a unique combination to attach the documents. They should be provided the same values consistently for a given object record so that previously attached documents (if any) are retrieved using the same combination of values.

8. Click **Save and Close**.

## Task 2: Create a Mashup To Embed Managed Attachments Page

## Task 3: Extend Specific Business Object To Show Managed Attachments Page


## Acknowledgements

* **Authors-** Ratheesh Pai, Senior Principal Member Technical Staff, Oracle WebCenter Content
* **Contributors-** Ratheesh Pai, Rajiv Malhotra, Vinay Kumar
* **Last Updated By/Date-** Ratheesh Pai, July 2025

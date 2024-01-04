# General
The API follow the REST convention.

# Domain-specific URLs
The format now includes domain (e.g. payment) in the URL, e.g., /api/v1/{domain}/

# Request

## Common Fields
- request_id: it should be required for most POST APIs

## Filtering
Filtering is done by adding one or more filter params in the List API
```
GET /api/v1/payments/?from_created_at=YYYYMMDD&to_created_at=YYYYMMDD
```

## Pagination
All `List` endpoints must support pagination (e.g. GET /api/v1/payments).  Two optional query parameters (page_size and page_num) are supported in the request with the default page_size=100 and page_num=0.  The response should look like this: 
```
{ 
   "items": [ 
      { ...object1... }, 
      { ...object2... },
      ... 
      { ...objectN... } 
   ], 
   "has_more" : true
}
```

## Date/time related fields
- Date fields are encoded as 'YYYY-MM-DD', e.g. "2023-04-12".  Naming wise "xxx_date", e.g. "payment_date"
- Date time fields are encoded as "YYYY-MM-DDTHH:mm:ss", e.g. 2021-04-12T23:11:00".  The timezone is UTC
- Date time fields have field names with the "_at" suffix.  e.g.:
  - created_at
  - updated_at
  - expires_at

## Metadata
The metadata field is a map of key/value pairs both of type String.
There are restrictions on the length of the key and the value. (K:10,V:500).
The metadata should not be processed/interpreted by server and therefore no restrictions are enforced on the format



# Response
## Body
For all the APIs, the response body has a consistent base structure in JSON format, while the difference between responses from different APIs lies in the structure of `data` field. 
```
{
    "code" : "{error_code}",
    "source": "{source_field}",
    "message" : "{error_message}",
    "data" : {
        // actual response data, which will vary depending on the specific API endpoint that was called.
    }
}
```
### Success response
For success response, `code` is always with value `success`  
```
{
    "code" : "success",
    "source": null,
    "message": null,
    "data": { // actual response data }
}
```
### Error codes and error handling
For 4xx and 5xx http status, the json response should contain the following fields:
- code: Error code in String format
- source: Path to the JSON field where the error occurred
- message: Description of the error including troubleshooting hints

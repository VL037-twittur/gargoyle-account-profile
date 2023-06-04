# Account Profile Controller

- [Get Accounts](#get-accounts)
- [Create Account Profile](#create-account-profile)
- [Get Account By Username](#get-account-by-username)
- [Get Account By ID](#get-account-by-id)
- [Add Tweet Count](#add-tweet-count)
- [Subtract Tweet Count](#subtract-tweet-count)
- [Follow Account](#follow-account)
- [Unfollow Account](#unfollow-account)
- [Get Account Followers](#get-account-followers)
- [Get Account Following](#get-account-following)
- [Get All Account Followers](#get-all-account-followers)

---

## <a name="get-accounts"></a> Get Accounts

**Http Method**: `GET`

**Endpoint**: `/api/v1/accounts`

**Request Param**:

| Request Param | Data Type        |
|---------------|------------------|
| pageNumber    | int, default: 0  |
| pageSize      | int, default: 10 |

**Request Body**: -

**Response**:

- response sample &rarr; `/api/v1/accounts?pageNumber=0&pageSize=5`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [
    {
      "id": "6c367104-14c1-46d8-88d1-df90c3a33ea8",
      "createdDate": "2023-05-14T22:21:50.283874",
      "createdBy": "997c11bb-ffc5-4265-a300-1f723c84c2f2",
      "updatedDate": "2023-05-14T22:21:50.283874",
      "updatedBy": "997c11bb-ffc5-4265-a300-1f723c84c2f2",
      "username": "smartdrawwww3",
      "accountName": "smartdrawwww3",
      "bio": "I'm Smart Draw 3",
      "tweetsCount": 1,
      "followersCount": 0,
      "followingCount": 2
    },
    {
      "id": "4f71a9f6-ad97-4afc-a309-7070a7a4b09e",
      "createdDate": "2023-05-15T15:37:05.571779",
      "createdBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
      "updatedDate": "2023-05-15T15:37:05.571779",
      "updatedBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
      "username": "smartdrawwww2",
      "accountName": "smartdrawwww2",
      "bio": "I'm Smart Draw 2",
      "tweetsCount": 23,
      "followersCount": 2,
      "followingCount": 0
    },
    {
      "id": "2fd8b41f-82c8-4619-a388-229b1081cc07",
      "createdDate": "2023-06-01T09:58:28.934724",
      "createdBy": "67aa3094-440b-420f-8d05-b856844d4c14",
      "updatedDate": "2023-06-01T09:58:28.934724",
      "updatedBy": "67aa3094-440b-420f-8d05-b856844d4c14",
      "username": "smartdrawwww4",
      "accountName": "SM4",
      "bio": "I'm Smart Draw 4",
      "tweetsCount": 0,
      "followersCount": 0,
      "followingCount": 0
    }
  ],
  "pageMetaData": {
    "pageNumber": 0,
    "pageSize": 5,
    "totalRecords": 3
  }
}
```

- response sample &rarr; empty `content`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [],
  "pageMetaData": {
    "pageNumber": 0,
    "pageSize": 5,
    "totalRecords": 0
  }
}
```

## <a name="create-account-profile"></a> Create Account Profile

**Http Method**: `POST`

**Endpoint**: `/api/v1/accounts`

**Request Param**: -

**Request Body**:

```json
{
  "accountCredentialId": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
  "username": "smartdrawwww3",
  "accountName": "smartdrawwww3",
  "createdBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
  "createdDate": "2023-05-15T15:37:05.571779",
  "updatedBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
  "updatedDate": "2023-05-15T15:37:05.571779"
}
```

**Response**:

- response sample &rarr; `/api/v1/accounts`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "data": {
    "id": "4f71a9f6-ad97-4afc-a309-7070a7a4b09e",
    "createdDate": "2023-05-15T15:37:05.571779",
    "createdBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
    "updatedDate": "2023-05-15T15:37:05.571779",
    "updatedBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
    "username": "smartdrawwww3",
    "accountName": "smartdrawwww3",
    "bio": "I'm Smart Draw 2",
    "tweetsCount": 0,
    "followersCount": 0,
    "followingCount": 0
  }
}
```

## <a name="get-account-by-username"></a> Get Account By Username

**Http Method**: `GET`

**Endpoint**: `/api/v1/accounts/@{username}`

**Request Param**: -

**Request Body**: -

**Response**:

- response sample &rarr; `/api/v1/accounts/@smartdrawwww2`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "data": {
    "id": "4f71a9f6-ad97-4afc-a309-7070a7a4b09e",
    "createdDate": "2023-05-15T15:37:05.571779",
    "createdBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
    "updatedDate": "2023-05-15T15:37:05.571779",
    "updatedBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
    "username": "smartdrawwww2",
    "accountName": "smartdrawwww2",
    "bio": "I'm Smart Draw 2",
    "tweetsCount": 23,
    "followersCount": 2,
    "followingCount": 0
  }
}
```

- response sample &rarr; `username` not found

```json
{
  "code": 404,
  "status": "NOT_FOUND",
  "error": "account not found"
}
```

## <a name="get-account-by-id"></a> Get Account By ID

**Http Method**: `GET`

**Endpoint**: `/api/v1/accounts/{id}`

**Request Param**: -

**Request Body**: -

**Response**:

- response sample &rarr; `/api/v1/accounts/4f71a9f6-ad97-4afc-a309-7070a7a4b09e`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "data": {
    "id": "4f71a9f6-ad97-4afc-a309-7070a7a4b09e",
    "createdDate": "2023-05-15T15:37:05.571779",
    "createdBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
    "updatedDate": "2023-05-15T15:37:05.571779",
    "updatedBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
    "username": "smartdrawwww2",
    "accountName": "smartdrawwww2",
    "bio": "I'm Smart Draw 2",
    "tweetsCount": 23,
    "followersCount": 2,
    "followingCount": 0
  }
}
```

- response sample &rarr; `username` not found

```json
{
  "code": 404,
  "status": "NOT_FOUND",
  "error": "account not found"
}
```

## <a name="add-tweet-count"></a> Add Tweet Count

**Http Method**: `POST`

**Endpoint**: `/api/v1/accounts/@{username}/add-tweet`

**Request Param**: -

**Request Body**: -

**Response**:

- response sample &rarr; `/api/v1/accounts/@smartdrawwww2/add-tweet`

```json
{
  "code": 200,
  "status": "OK",
  "error": null
}
```

## <a name="subtract-tweet-count"></a> Subtract Tweet Count

**Http Method**: `POST`

**Endpoint**: `/api/v1/accounts/@{username}/subtract-tweet`

**Request Param**: -

**Request Body**: -

**Response**:

- response sample &rarr; `/api/v1/accounts/@smartdrawwww2/subtract-tweet`

```json
{
  "code": 200,
  "status": "OK",
  "error": null
}
```

## <a name="follow-account"></a> Follow Account

**Http Method**: `POST`

**Endpoint**: `/api/v1/accounts/_follow`

**Request Param**: -

**Request Body**:

```json
{
  "followerId": "6c367104-14c1-46d8-88d1-df90c3a33ea8",
  "followedId": "4f71a9f6-ad97-4afc-a309-7070a7a4b09e"
}
```

**Response**:

- response sample &rarr; `/api/v1/accounts/_follow`

```json
{
  "code": 200,
  "status": "OK",
  "error": null
}
```

- response sample &rarr; already follow

```json
{
  "code": 409,
  "status": "CONFLICT",
  "error": "already followed the account"
}
```

## <a name="unfollow-account"></a> Unfollow Account

**Http Method**: `POST`

**Endpoint**: `/api/v1/accounts/_unfollow`

**Request Param**: -

**Request Body**:

```json
{
  "followerId": "6c367104-14c1-46d8-88d1-df90c3a33ea8",
  "followedId": "4f71a9f6-ad97-4afc-a309-7070a7a4b09e"
}
```

**Response**:

- response sample &rarr; `/api/v1/accounts/_unfollow`

```json
{
  "code": 200,
  "status": "OK",
  "error": null
}
```

## <a name="get-account-followers"></a> Get Account Followers

**Http Method**: `GET`

**Endpoint**: `/api/v1/accounts/@{username}/followers`

**Request Param**:

| Request Param | Data Type        |
|---------------|------------------|
| pageNumber    | int, default: 0  |
| pageSize      | int, default: 10 |

**Request Body**: -

**Response**:

- response sample &rarr; `/api/v1/accounts/@smartdrawwww2/followers`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [
    {
      "id": "6c367104-14c1-46d8-88d1-df90c3a33ea8",
      "createdDate": "2023-05-14T22:21:50.283874",
      "createdBy": "997c11bb-ffc5-4265-a300-1f723c84c2f2",
      "updatedDate": "2023-05-14T22:21:50.283874",
      "updatedBy": "997c11bb-ffc5-4265-a300-1f723c84c2f2",
      "username": "smartdrawwww3",
      "accountName": "smartdrawwww3"
    }
  ],
  "pageMetaData": {
    "pageNumber": 0,
    "pageSize": 10,
    "totalRecords": 1
  }
}
```

- response sample &rarr; empty `content`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [],
  "pageMetaData": {
    "pageNumber": 0,
    "pageSize": 10,
    "totalRecords": 0
  }
}
```

## <a name="get-account-following"></a> Get Account Following

**Http Method**: `GET`

**Endpoint**: `/api/v1/accounts/@{username}/following`

**Request Param**:

| Request Param | Data Type        |
|---------------|------------------|
| pageNumber    | int, default: 0  |
| pageSize      | int, default: 10 |

**Request Body**: -

**Response**:

- response sample &rarr; `/api/v1/accounts/@smartdrawwww3/following`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [
    {
      "id": "4f71a9f6-ad97-4afc-a309-7070a7a4b09e",
      "createdDate": "2023-05-15T15:37:05.571779",
      "createdBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
      "updatedDate": "2023-05-15T15:37:05.571779",
      "updatedBy": "7b9f6d26-652b-4456-9291-1e6b0f5f2f0d",
      "username": "smartdrawwww2",
      "accountName": "smartdrawwww2"
    }
  ],
  "pageMetaData": {
    "pageNumber": 0,
    "pageSize": 10,
    "totalRecords": 1
  }
}
```

- response sample &rarr; empty `content`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [],
  "pageMetaData": {
    "pageNumber": 0,
    "pageSize": 10,
    "totalRecords": 0
  }
}
```

## <a name="get-all-account-followers"></a> Get All Account Followers

**Http Method**: `GET`

**Endpoint**: `/api/v1/accounts/@{id}/all-followers`

**Request Param**: -

**Request Body**: -

**Response**:

- response sample &rarr; `/api/v1/accounts/4f71a9f6-ad97-4afc-a309-7070a7a4b09e/all-followers`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [
    {
      "id": "6c367104-14c1-46d8-88d1-df90c3a33ea8",
      "createdDate": "2023-05-14T22:21:50.283874",
      "createdBy": "997c11bb-ffc5-4265-a300-1f723c84c2f2",
      "updatedDate": "2023-05-14T22:21:50.283874",
      "updatedBy": "997c11bb-ffc5-4265-a300-1f723c84c2f2",
      "username": "smartdrawwww3",
      "accountName": "smartdrawwww3"
    }
  ],
  "pageMetaData": null
}
```

- response sample &rarr; empty `content`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [],
  "pageMetaData": null
}
```

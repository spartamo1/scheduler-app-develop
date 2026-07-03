
# Architecture
## ERD
[ERD Cloud Snapshot JSON](./erd-snapshot.json)

![img.png](img.png)

## API Spec

### 일정 목록 조회

| 항목              | 내용               |
| --------------- | ---------------- |
| Method          | `GET`            |
| URL             | `/schedules`     |
| 설명              | 전체 일정 목록을 조회합니다. |
| Request         | 없음               |
| Response Status | `200 OK`         |

#### Response

```json
[
  {
    "content": "string",
    "createdBy": "string",
    "title": "string",
    "createdAt": "2013-08-13T01:53:32.467Z",
    "updatedAt": "2012-11-20T23:02:58.020Z",
    "comments": [
      {
        "content": "string",
        "createdBy": "string",
        "createdAt": "2026-03-27T08:38:52.241Z",
        "updatedAt": "2016-06-02T04:22:53.281Z"
      }
    ]
  }
]
```

---

### 일정 생성

| 항목              | 내용             |
| --------------- | -------------- |
| Method          | `POST`         |
| URL             | `/schedules`   |
| 설명              | 새로운 일정을 생성합니다. |
| Request         | 명세 없음          |
| Response Status | `201 Created`  |

#### Response

```json
{
  "id": "할일 id",
  "title": "할일 제목",
  "content": "할일 내용"
}
```

---

### 일정 단건 조회

| 항목              | 내용                |
| --------------- | ----------------- |
| Method          | `GET`             |
| URL             | `/schedules/{id}` |
| 설명              | 특정 일정 하나를 조회합니다.  |
| Path Variable   | `id`              |
| Request         | 없음                |
| Response Status | `200 OK`          |

#### Parameters

| 이름   | 위치   | 필수 여부 | 타입     | 설명        |
| ---- | ---- | ----- | ------ | --------- |
| `id` | path | 필수    | string | 조회할 일정 ID |

#### Response

```json
{
  "id": 1,
  "title": "본문 제목",
  "content": "본문 내용"
}
```

---

### 일정 수정

| 항목              | 내용                |
| --------------- | ----------------- |
| Method          | `PUT`             |
| URL             | `/schedules/{id}` |
| 설명              | 특정 일정을 수정합니다.     |
| Path Variable   | `id`              |
| Request         | 명세 없음             |
| Response Status | `200 OK`          |

#### Parameters

| 이름   | 위치   | 필수 여부 | 타입     | 설명        |
| ---- | ---- | ----- | ------ | --------- |
| `id` | path | 필수    | string | 수정할 일정 ID |

#### Response

```json
{
  "id": 1,
  "title": "본문 제목",
  "content": "본문 내용"
}
```

---

### 일정 삭제

| 항목              | 내용                |
| --------------- | ----------------- |
| Method          | `DELETE`          |
| URL             | `/schedules/{id}` |
| 설명              | 특정 일정을 삭제합니다.     |
| Path Variable   | `id`              |
| Request         | 없음                |
| Response Status | `204 No Content`  |

#### Parameters

| 이름   | 위치   | 필수 여부 | 타입     | 설명        |
| ---- | ---- | ----- | ------ | --------- |
| `id` | path | 필수    | string | 삭제할 일정 ID |

#### Response

```text
No Content
```
# Organize Conference
Simple Rest API for organizing and planning conferences & meetings. 

## How it works
We have 3 simple json objecst: Conference, Topic, Speaker. 

```
Topic:{
  "id": 2,
  "title": "What is 'Reactive' programming?",
  "speaker": {
    "id": 7,
    "fullName": "Eric Gutebach"
  },
  "startTime": "15:20",
  "endTime": "16:05",
  "conferenceId": 1
}

Speaker:{
  "id": 7,
  "fullName": "Eric Gutebach",
  "topics": [
    {
      "id": 2,
      "title": "What is 'Reactive' programming?",
      "speaker": {
        "id": 7,
        "fullName": "Eric Gutebach"
      },
      "startTime": "15:20",
      "endTime": "16:05",
      "conferenceId": 1
    },
    {
      "id": 9,
      "title": "Power of Open Source",
      "speaker": {
        "id": 7,
        "fullName": "Eric Gutebach"
      },
      "startTime": "12:30",
      "endTime": "13:00",
      "conferenceId": 2
    }
  ],
  "country": "Germany",
  "email": "eric@yahoo.com",
  "about": "Works at munich"
}

Conference:{
  "id": 1,
  "title": "Reactive Java",
  "topics": [
    {
      "id": 2,
      "title": "What is 'Reactive' programming?",
      "speaker": {
        "id": 7,
        "fullName": "Eric Gutebach"
      },
      "startTime": "15:20",
      "endTime": "16:05",
      "conferenceId": 1
    },
    {
      "id": 3,
      "title": "WhatsApp watches you",
      "speaker": {
        "id": 9,
        "fullName": "Teresa May"
      },
      "startTime": null,
      "endTime": null,
      "conferenceId": 1
    }
  ],
  "startDate": "Mon Aug 05 09:00:00 EET 2019",
  "endDate": "Mon Aug 07 17:30:00 EET 2019"
}
```

## Urls
- The main url is ``` your-domain/api/ ```.
- For _Speaker_'s,  add ```speakers```; for _Conference_'s, add ```conferences``` and for _Topic_'s, add ```topics``` to the link. 
- For getting the objects, use **GET** http method. For saving, send the json object with **Post** method to the same url. 
- If you want to get a specific object, add the id to the end of the url like ``` your-domain/api/speakers/10 ```. 
- **You should know** that with post method, _null_ properties will be saved as null to database (except id as you can guess). 
- _Spring JPA_ does not have **Partial Update** feature so I did some work about it. The link is ``` your-domain/api/speakers/10 ``` 
with **Patch** http method. For more detail, check the section below. 


## Some important points
- This API **has no authentication** feature _yet_ so **anyone** who has access to the API **can manipulate everything**. 
- If you check the source code, you will notice that no entity is cascaded. That means if you try to 
update a *Topic* object's property in *Conference* object, it is not going to happen. This also means that you can not 
set *Conference* objects' topics directly too. Because **_Speaker_ and _Conference_ objects are mapped by _Topic_**. 
If you want to set or change a _Speaker_'s topic or _Conference_'s topics, you need to manage it from _Topic_ object. 
The main reason it is this way is simply this project is not aimed to be prepared for production _(for now)_ and 
I did not invest my time on those features.
- It is unfortunate that _Spring_ does not have a feature or one clear path for **Partial Update**. So my solution is: 
  - recieve the object via request
  - pull the object with the same id from database
  - write recieved object's non-null properties onto object from database by _BeanUtils_ of _Spring_
  - persist the updated object <br/>
This is not the best solution for partial update since it has concurrency problems, for example after you pulled the object 
from database it might be updated by someone else before you persist so the update will be lost. However, this probably would 
not be a problem even if we would use this project as product.




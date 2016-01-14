### Task description

#### Before you start
1. Send me email with time estimation (hours) and delivery dead-line (real date and time).

#### After you finish
1. Send me signed APK file.
3. Upload source code on you Git (any repository) account and provide link.
2. Notice how many hours you have spent indeed.

#### Essential
1. Don't worry about appearance and design, use default android UI components.
2. Add module https://github.com/psyh/testlib
3. Use API.class to use the module as API-emulator. 
4. API generate 20 results and start Update Service. You have to initialisate it and generate results. See below.
5. Remove results from list of results when user pushed "like", "dislike" buttons or Person got "removed" status.
6. If some person got status "like" and user liked this person before - show system notification with sound and vibration - This is MATCH. 
7. If person is in results list now has already status "like" and user pushed "like" button show MATCH Screen.
8. If some person got status "removed" - show system notification without sound and vibration. 
9. Use Google Maps to show markers - small person's photos to show person's location. Don't forget update location of Person that was updated by API.
10. Show "heart" icon above Person's photo if Person existing in result list got status "like". (Use any icon you can find)

#### Required
1. Do not change the code of the module (even if you think you can do better)
2. Data has to be locally saved
3. minSdkVersion: 16
4. targetSdkVersion: 23+

#### Desirable
to demonstrate your skills in using

1. activities, fragments and its lifecycles.
2. multithreading
3. adapters
4. resources (layouts, styles etc)
5. maps


#### Will be Perfect (but not required)
1. MVP
2. DI
2. TDD
3. RX
4. Attractive UI


### Test Application Scheme

![alt text](https://raw.githubusercontent.com/psyh/testlib/master/scheme.png "Test Scheme")

### Module description
This module imitate the work of API.
The module can generate 20 different persons with random location.
Example: ```{"id":15,"location":"38.76553,-9.119707","photo":"https://goo.gl/zhbWp2","status":"none"}```

After persons generation client can subscribe to persons updates. It will trigger Person Update Service it will send updates every 2 seconds until client unsubscribe.

Use only API.class to communicate with this module. 

 1 .  Initialisation is required:
```
API.INSTANCE.init(getApplicationContext());
```
 2 .  Generate persons base. It will clear all previous results:
```
refreshPersons(SuccessCallback successCallback)
```
 3 .  Get 10 results. (Set page-num. 0, 1,...):
```
getPersons(int page, PersonsExtendedCallback callback)
```
 4 .  Subscribe for updates:
```
subscribeUpdates(UpdateServiceListener listener)
```
 5 .  Unsubscribe :
```
unSubscribeUpdates()
```



##### Recommendations
*Clean Code: A Handbook of Agile Software Craftsmanship
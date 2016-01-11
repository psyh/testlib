### Task description
1. Doesn't matter how it will look like. Use default android UI components.
2. Add module https://github.com/psyh/testlib
3. Use API.class to use the module as API-emulator. 
4. API generate 20 results and start Update Service. You have to initialisate it and generate results. See below.
5. Remove results from list of results when user pushed "like", "dislike" buttons or Person got "removed" status.
6. If person user liked befor got status "like" you have MATCH - show system notification with sound and vibration. 
7. If person is showing now in results list has already status "like" and user pushed "like" button  show MATCH Screen.
8. If some person got status "removed" - show system notification without sound and vibration. 

### Test Application Scheme

![alt text](https://raw.githubusercontent.com/psyh/testlib/master/scheme.png "Test Scheme")

### Module description
This module imitate the work of API.
The module can generate 20 different persons with random location.
Example: ```{"id":15,"location":"38.76553,-9.119707","photo":"https://goo.gl/zhbWp2","status":"none"}```

After persons generation client can subscribe to persons updates. It will trigger Person Update Service it will send updates every 2 seconds until client unsubscribe.

Use only API.class to communicate with this module. 

1. Initialisation is required:
```
API.INSTANCE.init(getApplicationContext());
```
2. Generate persons base. It will clear all previous results:
```
refreshPersons(SuccessCallback successCallback)
```
3. Get 10 results. (Set page-num. 0, 1,...):
```
getPersons(int page, PersonsExtendedCallback callback)
```
4. Subscribe for updates:
```
subscribeUpdates(UpdateServiceListener listener)
```
4. Unsubscribe :
```
unSubscribeUpdates()
```

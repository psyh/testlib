This module imitate the work of API.
The module can generate 20 different persons with random location.
Example: ```{"id":15,"location":"38.76553,-9.119707","photo":"http://cs309123.vk.me/v309123012/9122/r7oKX3HSaIg.jpg","status":"none"}```

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

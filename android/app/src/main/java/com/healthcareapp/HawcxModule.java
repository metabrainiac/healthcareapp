package com.healthcareapp;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.hawcx.HawcxInitializer;
import com.hawcx.auth.SignIn;
import com.hawcx.utils.AuthErrorHandler;

public class HawcxModule extends ReactContextBaseJavaModule implements SignIn.SignInCallback {
     private ReactApplicationContext reactContext;
     private Promise currentPromise;

     public HawcxModule(ReactApplicationContext reactContext) {
         super(reactContext);

         this.reactContext = reactContext;
         HawcxInitializer.getInstance().init(reactContext, "LmVKd2x5azBPd2lBUVFPRzdzRFlOTFFpREszc1NNakl6U3ZvRG9UWEdHTzl1RTVjdjNfdW81OFl0WmxJWE5mSGFjbnBnbzY2MlV1UjZYekRQWFNxTE9pbXM5WF90dk8xanJYTk91T2V5SHRUd0ZiSG1PUEg3Y1BCR3MtMFpicEE4QUxNSjRJU09JT2RFQkNSNHR0NzRmaGlDOVpvMEdRck9raFU4V3dycS13T3ZReTg3LlpyLXBRQS5wWXA0YXJMWXhYcUFrZEFOUlNLanhIeE5kdWRGVHBxOHpaZS1uZkk5Skkwcjh0WnNsbkhBZEZDeU82VEw3SDFLdFpzb1d4eG9MX2gxeDNycF9pR0ktdw==");
     }

     @Override
     public String getName() {
         return "HawcxModule";
     }


     @ReactMethod
     public void checkLastUser(Promise promise) {
         SignIn loginAct = HawcxInitializer.getInstance().getSignIn();
         loginAct.checkLastUser((SignIn.SignInCallback) reactContext);
         promise.resolve(null);
     }

     @ReactMethod
         public void login(String email, Promise promise) {
             currentPromise = promise;
             SignIn loginAct = HawcxInitializer.getInstance().getSignIn();
             loginAct.signIn(email, this);
         }

    @Override
    public void showEmailSignInScreen() {
        Log.d("TEST", "showEmailSignInScreen() called");
    }

    @Override
         public void onSuccessfulLogin(String loggedInEmail) {
             if (currentPromise != null) {
                 currentPromise.resolve(loggedInEmail);
                 currentPromise = null;
             }
         }

    @Override
    public void showError(AuthErrorHandler.SignInErrorCode signInErrorCode, String s) {
        if (currentPromise != null) {
            currentPromise.reject("LOGIN_ERROR", s);
            currentPromise = null;
        }
    }

    @Override
    public void showError(String errorMessage) {
        if (currentPromise != null) {
            currentPromise.reject("LOGIN_ERROR", errorMessage);
            currentPromise = null;
        }
    }

    @Override
    public void initiateBiometricLogin(Runnable runnable) {

    }
    // Add other methods for different HawcxAuth features
}
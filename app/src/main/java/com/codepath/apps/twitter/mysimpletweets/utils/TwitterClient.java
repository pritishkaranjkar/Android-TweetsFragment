package com.codepath.apps.twitter.mysimpletweets.utils;

/**
 * Created by kapritish on 10/30/16.
 */
import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 *
 * This is the object responsible for communicating with a REST API.
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes:
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 *
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 *
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "https://api.twitter.com/1.1";
    public static final String REST_CONSUMER_KEY = "fx4Wge8dyAaQsNLYmr3X7RCbH";
    public static final String REST_CONSUMER_SECRET = "hp3YHIc1Isr5SEg0EX8i4op0cVUkqC0mXBG3yFxsqrLEo3DV3A";
    public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets"; // Change this (here and in manifest)

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void getHomeTimeline(long since_id, long max_id, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("count", 25);
        if (max_id > 0) {
            // add to the list tweets older than the currently displayed tweets
            params.put("max_id", max_id);
        } else {
            // start with the newest tweets
            params.put("since_id", since_id);
        }
        // execute the request
        getClient().get(apiUrl, params, handler);
    }

    public void getMentionsTimeline(long since_id, long max_id, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        RequestParams params = new RequestParams();
        params.put("count", 25);
        if (max_id > 0) {
            // add to the list tweets older than the currently displayed tweets
            params.put("max_id", max_id);
        } else {
            // start with the newest tweets
            params.put("since_id", since_id);
        }
        // execute the request
        getClient().get(apiUrl, params, handler);
    }

    public void getUserTimeline(long since_id, long max_id, String screenName, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        RequestParams params = new RequestParams();
        params.put("screen_name", screenName);
        params.put("count", 25);
        if (max_id > 0) {
            // add to the list tweets older than the currently displayed tweets
            params.put("max_id", max_id);
        } else {
            // start with the newest tweets
            params.put("since_id", since_id);
        }
        // execute the request
        getClient().get(apiUrl, params, handler);
    }

    public void getUserInfo(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("account/verify_credentials.json");
        getClient().get(apiUrl, null, handler);
    }

    public void getOtherUserInfo(String screenName, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("users/show.json");
        RequestParams params = new RequestParams();
        params.put("user_id", screenName);
        params.put("screen_name", screenName);
        getClient().get(apiUrl, params, handler);
    }

    // to post a tweet
    public void postTweet(String myTweet, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", myTweet);
        // execute the request
        getClient().post(apiUrl, params, handler);
    }

}
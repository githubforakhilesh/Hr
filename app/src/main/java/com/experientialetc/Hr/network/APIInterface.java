package com.experientialetc.Hr.network;


import com.experientialetc.Hr.response.LoginApiResponse;
import com.experientialetc.Hr.response.TokenApiResponse;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface APIInterface {

    @FormUrlEncoded
    @POST("api/login.php")
    Call<LoginApiResponse> loginApi(@Header("Authorization") String access_token,
                                    @Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("jwt/jwtAuthorize.php")
    Call<TokenApiResponse> jwtAuthorizeApi(@Field("user") String user,
                                           @Field("password") String pass);

    @POST("http://10.188.7.135:44442/api/Login")
    Call<EventTokenApiResponse> jwtAuthorizeApiForEvent(@Body JsonObject data);

    /*  @FormUrlEncoded
    @POST("api/presenter/getAgendaApi.php")
    Call<AgendaApiResponse> getAgendaApi(@Header("Authorization") String access_token,
                                         @Field("event_id") String eventId);

    @FormUrlEncoded
    @POST("api/presenter/getParticipantApi.php")
    Call<GetParticipantResponse> getParticipantApi(@Header("Authorization") String access_token,
                                                   @Field("event_id") String eventId);


    @POST("http://10.188.7.135:44442/api/Booking/SearchEventAPI")
    Call<SearchEventResponse> searchEventApi(@Header("Authorization") String access_token,
                                             @Query("event_id") String eventId,
                                             @Query("datetime") String time);

//    @FormUrlEncoded
//    @POST("api/presenter/searchEventApi.php")
//    Call<SearchEventResponse> searchEventApi(@Header("Authorization") String access_token,
//                                             @Field("event_id") String eventId,
//                                             @Field("datetime") String time);


    @FormUrlEncoded
    @POST("api/presenter/searchFile.php")
    Call<SearchFileResponse> searchFileApi(@Header("Authorization") String access_token,
                                           @Field("query") String query);

    @Multipart
    @POST("api/presenter/mediaUploadAPI.php")
    Call<UploadMediaApiResponse> mediaUploadAPI(@Header("Authorization") String access_token,
                                                @Part MultipartBody.Part imagePic,
                                                @Part("eventID") RequestBody id);

    @FormUrlEncoded
    @POST("jwt/jwtAuthorize.php")
    Call<TokenApiResponse> jwtAuthorizeApi(@Field("user") String user,
                                           @Field("pass") String pass);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("http://10.188.7.135:44442/api/Login")
    Call<EventTokenApiResponse> jwtAuthorizeApiForEvent(@Body JsonObject data);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/presenter/saveagenda.php")
    Call<SaveAgendaApiResponse> saveAgendaApi(@Header("Authorization") String access_token,
                                              @Body JsonObject agendaList);

    @FormUrlEncoded
    @POST("api/presenter/triggerSignoffApi.php")
    Call<SignOffResponse> signOffApi(@Header("Authorization") String access_token,
                                     @Field("event_id") String eventId,
                                     @Field("feedback_flag") String feedbackFlag);

    @FormUrlEncoded
    @POST("api/presenter/feedbackRating.php")
    Call<FeedbackRatingResponse> feedbackRatingApi(@Header("Authorization") String access_token,
                                                   @Field("eventId") String eventId);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/presenter/sendFeedbackMailApi.php")
    Call<SendFeedbackApiResponse> sendFeedbackApi(@Header("Authorization") String access_token,
                                                  @Body JsonObject emailJson);

    @FormUrlEncoded
    @POST("api/presenter/showFeedback.php")
    Call<ShowfeedbackResponse> showFeedbackApi(@Header("Authorization") String access_token,
                                               @Field("bookingId") String bookingId);

    @FormUrlEncoded
    @POST("api/presenter/addFeedbackApi.php")
    Call<AddFeedbackApiResponse> addFeedbackApi(@Header("Authorization") String access_token,
                                                @Field("bookingId") String bookingId,
                                                @Field("FeedbackQuestion") String FeedbackQuestion);

    @FormUrlEncoded
    @POST("api/presenter/delFeedbackApi.php")
    Call<DeleteFeedbackApiResponse> deleteFeedbackApi(@Header("Authorization") String access_token,
                                                      @Field("feedbackId") String feedbackId);


    @FormUrlEncoded
    @POST("api/presenter/editFeedbackApi.php")
    Call<EditFeedbackApiResponse> editFeedbackApi(@Header("Authorization") String access_token,
                                                  @Field("feedbackId") String feedbackId,
                                                  @Field("FeedbackQuestion") String FeedbackQuestion);
*/
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url() String fileUrl);
}

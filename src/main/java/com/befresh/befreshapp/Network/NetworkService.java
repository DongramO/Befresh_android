package com.befresh.befreshapp.Network;

import com.befresh.befreshapp.Account.AccountInfo;
import com.befresh.befreshapp.Account.ChangePw;
import com.befresh.befreshapp.Account.PassInfo;
import com.befresh.befreshapp.Community.CommunityModel.CommentSendInfo;
import com.befresh.befreshapp.Community.CommunityModel.SaveListMagazineInfo;
import com.befresh.befreshapp.Community.CommunityModel.SaveListMyRecipeInfo;
import com.befresh.befreshapp.Community.CommunityModel.SaveListRecommendInfo;
import com.befresh.befreshapp.Community.CommunityModel.addInfo;
import com.befresh.befreshapp.Community.CommunityModel.getCommunityMain;
import com.befresh.befreshapp.Community.CommunityModel.getMagazineDetail;
import com.befresh.befreshapp.Community.CommunityModel.getMagazineMain;
import com.befresh.befreshapp.Community.CommunityModel.getMembershipResult;
import com.befresh.befreshapp.Community.CommunityModel.getMyrecipeCommentResult;
import com.befresh.befreshapp.Community.CommunityModel.getRecipePhoto;
import com.befresh.befreshapp.Community.CommunityModel.getRecipePhotoDetail;
import com.befresh.befreshapp.Community.CommunityModel.getRestaurantContent;
import com.befresh.befreshapp.Community.CommunityModel.getRestaurantResult;
import com.befresh.befreshapp.Community.CommunityModel.setCommunityMylist;
import com.befresh.befreshapp.Community.CommunityModel.setReview;
import com.befresh.befreshapp.Login.LoginModel.LoginInfo;
import com.befresh.befreshapp.Login.LoginModel.UserInfo;
import com.befresh.befreshapp.Login.LoginModel.getLoginResult;
import com.befresh.befreshapp.Login.LoginModel.getSigninResult;
import com.befresh.befreshapp.Membership.FreshPeople.FreshPeople.FreshPeopleModel.UpdateResult;
import com.befresh.befreshapp.Membership.FreshPeople.FreshPeople.FreshPeopleModel.getMemberOutInfo;
import com.befresh.befreshapp.Membership.FreshPeople.FreshPeople.FreshPeopleModel.getMemberWeekInfo;
import com.befresh.befreshapp.Membership.MembershipModel.Category;
import com.befresh.befreshapp.Membership.MembershipModel.JoinForm;
import com.befresh.befreshapp.Membership.MembershipModel.MembershipModel.ScheduleInfo;
import com.befresh.befreshapp.Membership.MembershipModel.ScheduleResult;
import com.befresh.befreshapp.Membership.MembershipModel.getMemberJoinInfo;
import com.befresh.befreshapp.Membership.MembershipModel.getMembershipCheck;
import com.befresh.befreshapp.Membership.MembershipModel.getMembershipJoin;
import com.befresh.befreshapp.Mypage.MypageModel.CancleInfo;
import com.befresh.befreshapp.Mypage.MypageModel.getMyDeliveriedResult;
import com.befresh.befreshapp.Mypage.MypageModel.getMyPageResult;
import com.befresh.befreshapp.Mypage.MypageModel.getMyRecipeResult;
import com.befresh.befreshapp.Mypage.MypageModel.setLogout;
import com.befresh.befreshapp.Mypage.MypageModel.setMyPageRegist;
import com.befresh.befreshapp.Recipe.RecipeModel.Material;
import com.befresh.befreshapp.Recipe.RecipeModel.Overthirty;
import com.befresh.befreshapp.Recipe.RecipeModel.WriteRecipeInfo;
import com.befresh.befreshapp.Recipe.RecipeModel.WriteRecipeReview;
import com.befresh.befreshapp.Recipe.RecipeModel.getRecipeCardDetail;
import com.befresh.befreshapp.Recipe.RecipeModel.getRecipeContent;
import com.befresh.befreshapp.Recipe.RecipeModel.getRecipeMainWellbeing;
import com.befresh.befreshapp.Recipe.RecipeModel.getRecipeReview;
import com.befresh.befreshapp.Recipe.RecipeModel.getRecipeSearch;
import com.befresh.befreshapp.Search.SearchResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by idongsu on 2017. 6. 26..
 */

public interface NetworkService {
    @POST("/signin")
    Call<getSigninResult> getSigninResult(@Body UserInfo userInfo);

    @POST("/login")
    Call<getLoginResult> getLoginResult(@Body LoginInfo loginInfo);

    // savelist -> savedrecipe 변경
    @GET("/mypage/savedrecipe")
    Call<getMyPageResult> getMyPageResult(@Header("token") String token);

    // deliveriedRecipe 추가
    @GET("/mypage/deliveriedrecipe")
    Call<getMyDeliveriedResult> getMyDeliveriedResultCall(@Header("token") String token);

    @GET("/mypage/savlist/cancle")
    Call<getMyPageResult> getMypageResult(@Body CancleInfo cancleInfo);

    @GET("/mypage/receipt")
    Call<getMyRecipeResult> getMyRecipteResult();

    @GET("/mypage/logout")
    Call<setLogout> setLogout();

    @GET("/community/main")
    Call<getCommunityMain> getCommunityMain(@Header("token") String token);

//    이상해서 보류
//    @POST("community/mylist")
//    Calss<get>

    @GET("community/recipephoto/populity")
    Call<getRecipePhoto> getRecipePhoto(@Header("token") String token);



    @GET("/community/recipephoto/newest")
    Call<getRecipePhoto> getRecipePhoto2(@Header("token") String token);

//    @POST("community/recipephoto/newest/mylist")
//    Call<setCommunityMylist> setCommunityMylist(@Body addInfo addInfo);

    @Multipart
    @POST("community/recipephoto/registration")
    Call<setReview> setReview(@Part MultipartBody.Part file,
                              @Header("token") String token,
                              @Part("title") RequestBody title,
                              @Part("content") RequestBody content,
                              @Part("width") RequestBody width,
                              @Part("height") RequestBody height);

    @GET("community/recipephoto/content/{id}")
    Call<getRecipePhotoDetail> getRecipePhotoDetail(@Header("token") String token, @Path("id") int id);

    @POST("community/recipephoto/populity/mylist")
    Call<setCommunityMylist> setCommunityMylist(@Body addInfo addInfo);

    @GET("community/restaurant")
    Call<getRestaurantResult> getRestaurantResult(@Header("token") String token);

    @GET("community/restaurant/content/{id}")
    Call<getRestaurantContent> getRestaurantContent(@Header("token") String token, @Path("id") int id);

    @GET("community/magazine")
    Call<getMagazineMain> getMagazineMain(@Header("token") String token);

    @GET("community/magazine/content/{id}")
    Call<getMagazineDetail> getMagazineDetail(@Header("token") String token, @Path("id") int id);

    @POST("membership/join")
    Call<getMembershipJoin> getMembershipJoin(@Body Category category);

    @POST("membership/join/info")
    Call<getMemberJoinInfo> getMemeberJoinInfo(@Body UserInfo userinfo);

    @GET("recipe/main/wellbeing")
    Call<getRecipeMainWellbeing> getRecipeWell(@Header("token") String token);

    @GET("recipe/main/vegetarian")
    Call<getRecipeMainWellbeing> getRecipeVeg(@Header("token") String token);

    @POST("recipe/filter/wellbeing/time")
    Call<getRecipeMainWellbeing> getRecipeMainWellbeingfilter1(@Header("token") String token, @Body Overthirty overthirty);

    @POST("recipe/filter/vegetarian/time")
    Call<getRecipeMainWellbeing> getRecipeMainVegfilter1(@Header("token") String token, @Body Overthirty overthirty);

    @POST("recipe/filter/wellbeing/material")
    Call<getRecipeMainWellbeing> getRecipeMainWellBeingfilter2(@Header("token") String token, @Body Material material);

    @POST("recipe/filter/vegetarian/material")
    Call<getRecipeMainWellbeing> getRecipeMainVegfilter2(@Header("token") String token, @Body Material material);

    @GET("recipe/content/{id}")
    Call<getRecipeContent> getRecipeContent(@Header("token") String token, @Path("id") int id);

    @GET("recipe/content/card/{id}")
    Call<getRecipeCardDetail> getRecipeCardDetail(@Header("token") String token, @Path("id") int id);

    @GET("recipe/content/review/{id}")
    Call<getRecipeReview> getRecipeReview(@Header("token") String token, @Path("id") int id);

    @POST("recipe/content/review/registration")
    Call<WriteRecipeReview> WriteRecipeReview(@Header("token") String token, @Body WriteRecipeInfo writeRecipeInfo);

    @GET("recipe/search/{searhching}")
    Call<getRecipeSearch> getRecipeSearch(@Header("token") String token, @Path("search") String search);

    @GET("membership/check")
    Call<getMembershipCheck> getMembershipCheck(@Header("token") String token);

    @GET("membership")
    Call<getMembershipResult> getMembershipResult();

    @POST("membership/join")
    Call<getMembershipJoin> getMembershipJoin(@Header("token") String token, @Body Category category);

    @POST("membership/join/info")
    Call<getMemberJoinInfo> getMemeberJoinInfo(@Header("token") String token, @Body JoinForm form);

    @GET("membership/info/out")
    Call<getMemberOutInfo> getMemberOutInfo(@Header("token") String token);

    @GET("membership/info/{week}")
    Call<getMemberWeekInfo> getMemberWeekInfo(@Header("token") String token, @Path("week") int week);

    @POST("membership/schedule")
    Call<ScheduleResult> ScheduleResult(@Header("token") String token, @Body ScheduleInfo scheduleinfo);

    @POST("membership/refusedate")
    Call<UpdateResult> UpdateResult(@Header("token") String token, @Body ScheduleInfo scheduleinfo);

    // heart check
    @GET("/mypage/registmylist/{id}/{from}")
    Call<setMyPageRegist> setMyPageRegistCall(@Header("token") String token, @Path("id") int id, @Path("from") int from);

    @POST("/community/recipephoto/content/comment")
    Call<getMyrecipeCommentResult> getMyrecipeCommentResultCall(@Header("token") String token, @Body CommentSendInfo commentSendInfo);

    @GET("/community/savelist/recipephoto")
    Call<SaveListMyRecipeInfo> saveListRecipeInfoCall(@Header("token") String token);

    @GET("/community/savelist/magazine")
    Call<SaveListMagazineInfo> saveListMagazineInfoCall(@Header("token") String token);

    @GET("/community/savelist/restaurant")
    Call<SaveListRecommendInfo> saveListRecommendInfoCall(@Header("token") String token);

    @GET("recipe/search/{searching}")
    Call<SearchResult> SearchResult(@Header("token") String token, @Path("searching") String searching);

    @GET("mypage/accountinfo")
    Call<AccountInfo> AccountInfo(@Header("token") String token);

    @PUT("mypage/accountinfo/setpwd")
    Call<ChangePw> ChangePw(@Header("token") String token, @Body PassInfo passInfo);
}

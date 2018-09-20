package demosample.nirav.com.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import demosample.nirav.com.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class RetrofitSingleton {

    private static final String CONTENTYPE = "application/json";
    private static volatile RetrofitSingleton instance;
    private static String deviceId;

    private RetrofitSingleton() {
    }

    public static RetrofitSingleton getInstance() {
        if (instance == null) {
            instance = new RetrofitSingleton();
            deviceId = UUID.randomUUID().toString();
        }
        return instance;
    }

/*    private static OkHttpClient provideOkHttpClient1() {
        return new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    if (OnBoardingActivity.loginInfoDto != null) {
                        Request request = original.newBuilder()
                                .header("Authorization", "bearer "+ OnBoardingActivity
                                        .loginInfoDto.getAuthToken())
                                .header("Content-Type", CONTENTYPE)
                                .header("NST", NST)
                                .header("Accept", CONTENTYPE)
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    } else {
                        Request request = original.newBuilder()
                                .header("Content-Type", CONTENTYPE)
                                .header("Accept", CONTENTYPE)
                                .header("NST", NST)
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }


                })
                .build();
    }*/

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            builder.connectTimeout(80, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                       {
                            Request request = original.newBuilder()
                                    .header("Content-Type", CONTENTYPE)
                                    .header("Accept", CONTENTYPE)
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        }


                    });
            /*if (BuildConfig.DEBUG) {
                builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }*/
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create(provideGson());
    }

    private static Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    private static RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    private Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(provideGsonConverterFactory())
                .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
                .client(getUnsafeOkHttpClient().build())
                .build();
    }

    public IAppWebAPI provideApiService() {
        return provideRetrofit().create(IAppWebAPI.class);
    }

}

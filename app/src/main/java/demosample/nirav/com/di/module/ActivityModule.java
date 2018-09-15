/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package demosample.nirav.com.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import demosample.nirav.com.di.ActivityContext;
import demosample.nirav.com.login.ILoginPresenter;
import demosample.nirav.com.login.ILoginView;
import demosample.nirav.com.login.LoginPresenter;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    ILoginPresenter<ILoginView> provideLoginMvpPresenter(
            LoginPresenter<ILoginView> presenter) {
        return presenter;
    }

}

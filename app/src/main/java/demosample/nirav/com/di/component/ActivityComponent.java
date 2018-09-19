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

package demosample.nirav.com.di.component;


import dagger.Component;
import demosample.nirav.com.bardaiconnect.MainActivity;
import demosample.nirav.com.chooser.ChooseAddressDialog;
import demosample.nirav.com.di.PerActivity;
import demosample.nirav.com.di.module.ActivityModule;
import demosample.nirav.com.login.ChangePasswordActivity;
import demosample.nirav.com.login.ForgotPasswordActivity;
import demosample.nirav.com.login.LoginActivity;
import demosample.nirav.com.login.OtpActivity;
import demosample.nirav.com.login.ResetPasswordActivity;
import demosample.nirav.com.welcome_screen.OnBoardingActivity;

/**
 * Created by janisharali on 27/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(MainActivity mainActivity);
    void inject(ForgotPasswordActivity mainActivity);
    void inject(ChooseAddressDialog mainActivity);
    void inject(ResetPasswordActivity mainActivity);
    void inject(ChangePasswordActivity mainActivity);
    void inject(OtpActivity mainActivity);
    void inject(OnBoardingActivity mainActivity);
    void inject(LoginActivity mainActivity);



}

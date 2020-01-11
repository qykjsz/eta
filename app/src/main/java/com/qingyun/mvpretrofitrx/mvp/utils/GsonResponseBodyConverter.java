/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qingyun.mvpretrofitrx.mvp.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.qingyun.mvpretrofitrx.mvp.BaseResponseException;
import com.qingyun.mvpretrofitrx.mvp.base.BaseResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;


final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
  private final Gson gson;
  private final TypeAdapter<T> adapter;

  GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
    this.gson = gson;
    this.adapter = adapter;
  }

  @Override public T convert(ResponseBody value) throws IOException {

    try {
      String response = value.string();
      BaseResponse httpStatus = gson.fromJson(response, BaseResponse.class);
      if (httpStatus.getCode() == 200||httpStatus.getCode() == 1){

          MediaType contentType = value.contentType();
          Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
          InputStream inputStream = new ByteArrayInputStream(response.getBytes());
          Reader reader = new InputStreamReader(inputStream, charset);
          JsonReader jsonReader = gson.newJsonReader(reader);
          T finalObject = adapter.read(jsonReader);
          if (finalObject instanceof BaseResponse){
            if ((((BaseResponse) finalObject).getData())instanceof String&&TextUtils.isEmpty((String)(((BaseResponse) finalObject).getData())))
            {
              ((BaseResponse) finalObject).setData(((BaseResponse) finalObject).getMsg());
            }
          }
          return finalObject;

      }else {
        value.close();
        throw new BaseResponseException(httpStatus.getCode(), httpStatus.getMsg());
      }

    }finally {
      value.close();
    }




  }
}

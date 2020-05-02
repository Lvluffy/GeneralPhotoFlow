# GeneralPhotoFlow
通用图片查看

## gradle使用：

一、Project下的build.gradle文件下添加

allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}

二、Module下的build.gradle文件下添加

1.0.1之前

dependencies {
    
    implementation 'com.github.Lvluffy:GeneralPhotoFlow:1.0.1'
          
}

1.0.2之后

dependencies {
    
    implementation 'com.github.Lvluffy.GeneralPhotoFlow:photobrowselib:1.0.2'
       
    implementation 'com.github.Lvluffy.GeneralPhotoFlow:photopreviewlib:1.0.2'
             
}


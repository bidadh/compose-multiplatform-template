package com.ideabaker.kmp.translator

import android.os.Build

actual fun getPlatformName() : String = Build.MANUFACTURER +
" " + Build.VERSION.RELEASE
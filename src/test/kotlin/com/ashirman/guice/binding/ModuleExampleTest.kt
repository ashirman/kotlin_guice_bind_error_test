package com.ashirman.guice.binding

import com.google.inject.Module
import junit.framework.Assert.assertTrue
import org.junit.Test

class ModuleExampleTest {

    @Test
    fun `should run using Kotlin binder`() {
        Runner().run(listOf<Module>(KotlinBinderModuleExample()))
    }


    @Test
    fun `should run using non Kotlin binder`() {
        Runner().run(listOf<Module>(NonKotlinBinderModuleExample()))
    }
}

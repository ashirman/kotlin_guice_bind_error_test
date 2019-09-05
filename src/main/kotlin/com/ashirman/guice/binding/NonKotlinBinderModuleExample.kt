package com.ashirman.guice.binding

import com.authzee.kotlinguice4.KotlinModule
import com.google.inject.*
import com.google.inject.spi.Elements

class Runner {
    fun run(modules: Collection<Module>) {
        //Elements.getElements uses RecordingBinder inside. so any kotlin modules
        //will cache that binder forever...
        val elements = Elements.getElements(modules)

        Guice.createInjector(Stage.PRODUCTION, Module { it.requireExplicitBindings() }).createChildInjector(modules)
    }
}

open class Foo {
}

open class Bar @Inject constructor(private val foo: Foo) {
}

class KotlinBinderModuleExample : KotlinModule() {
    override fun configure() {
        bind<Foo>().`in`<Singleton>()
        bind(Bar::class.java).`in`(Scopes.SINGLETON)
    }
}

class NonKotlinBinderModuleExample : KotlinModule() {
    override fun configure() {
        bind(Foo::class.java).`in`(Scopes.SINGLETON)
        bind(Bar::class.java).`in`(Scopes.SINGLETON)
    }
}

fun main(args : Array<String>) {
    println("Hello,World!")
    println(f1(2 , 3))
    println(f2(4, 5))
    f3(1, 2, 3, 4)
}

fun f1(a: Int, b: Int): Int {
    return a + b
}

public fun f2(a: Int, b: Int): Int {
    return a + b
}
fun f3(vararg v: Int) {
    for (vv in v) {
        println(vv)
    }
}
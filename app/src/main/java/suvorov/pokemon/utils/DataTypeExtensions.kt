package suvorov.pokemon.utils

fun String?.stringEmptyIfNull(): String {
    return this ?: ""
}

fun Int?.intEmptyIfNull(): Int {
    return this ?: 0
}
package dark.composer.movie_db.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import dark.composer.movie_db.R
import javax.annotation.Nullable

fun Int.runtimeToHM(): String {
    return "${this / 60}h ${this % 60}m"
}

fun String.changeMoneyType(): String {
    var reversedSum = ""
    for (i in this.length - 1 downTo 0) {
        reversedSum += this[i]
    }
    var changedSum = ""
    if (reversedSum.length > 3) {
        var k = 2
        for (i in reversedSum.indices) {
            changedSum += reversedSum[i]
            if (i >= k) {
                changedSum += " "
                k += 3
            }
        }
        reversedSum = ""
        for (i in changedSum.length - 1 downTo 0) {
            reversedSum += changedSum[i]
        }
    } else {
        reversedSum = this
    }
    return reversedSum.trim()
}
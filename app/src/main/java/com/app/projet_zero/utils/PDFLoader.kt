import android.content.Context
import android.net.Uri
import java.io.InputStream

object PDFLoader {
    fun loadPDF(context: Context, uri: Uri): InputStream? {
        return context.contentResolver.openInputStream(uri)
    }
}

package fag.edu.com.gerenciadordefichadeaviario.Util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintToPdf {
    public static void print(ListView listView) {
        List<Bitmap> bitmaps = getItems(listView);
        PdfDocument document = new PdfDocument();
        PdfDocument.Page page = null;

        //ISSO AQUI TEM QUE SER WHILE MESMO - FICA MAIS FÁCIL DE CONTROLAR
        int i = 0;
        while(i < bitmaps.size()) {
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1400, 1979, i).create();
            page = document.startPage(pageInfo);
            if (page == null) {
                return;
            }

            //BITMAP DO MESMO TAMANHO QUE ESTÁ NA SUA LISTVIEW
            Bitmap bitmap = bitmaps.get(i);
            //NESSE VOCÊ CRIA UM BITMAP COM ESCALAS DETERMINADAS
            //Bitmap bitmap = Bitmap.createScaledBitmap(bitmaps.get(i), (pageInfo.getPageWidth()/2)+(pageInfo.getPageWidth()/3), (bitmaps.get(i).getHeight()*2), false);

            Canvas canvas = page.getCanvas();
            int positionX = (pageInfo.getPageWidth() / 2) - (bitmap.getWidth() / 2);

            try {
                //CADA LINHA AQUI ADICIONA UM BITMAP NO CANVAS DA PÁGINA
                canvas.drawBitmap(bitmap, positionX, 0, null);

                bitmap = bitmaps.get(i+1);
                canvas.drawBitmap(bitmap, positionX, bitmap.getHeight(), null);

                bitmap = bitmaps.get(i+2);
                canvas.drawBitmap(bitmap, positionX, bitmap.getHeight()*2, null);

                bitmap = bitmaps.get(i+3);
                canvas.drawBitmap(bitmap, positionX, bitmap.getHeight()*3, null);
            } catch (IndexOutOfBoundsException e) {
                //QUANDO A LISTA ESTOURAR NÃO VAI DAR MERDA. sÓ VAI FINALIZAR O DOCUMENTO E O LAÇO.
                document.finishPage(page);
                break;
            }

            //NO TRY/CATCH ALÍ EM CIMA EU PEGUEI 4 ITENS DA LISTVIEW. ESSE INCREMENTO É BASEADO NISSO.
            i = i+4;
            document.finishPage(page);
        }
        writeDocument(document);
    }

    private static  List<Bitmap> getItems(ListView listView) {
        //ISSO AQUI VAI TRANSFORMAR CADA ITEM DA LISTVIEW EM UM BITMAP
        ListAdapter adapter = listView.getAdapter();
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        for (int i=0; i<adapter.getCount(); i++) {
            View childView = adapter.getView(i, null, listView);
            childView.measure(View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bitmaps.add(childView.getDrawingCache());
        }
        return bitmaps;
    }

    private static void writeDocument(PdfDocument document) {
        //ISSO AQUI VAI ESCREVER O DOCUMENT DO METODO PRINCIPAL EM UM ARQUIVO .PDF CRIADO AQUI
        File pdfDir = new File(Environment.getExternalStorageDirectory(), "MyProject");
        if (!pdfDir.exists()){
            pdfDir.mkdir();
        }
        File pdfFile = new File(pdfDir, "myPdfFile_new.pdf");

        try {
            document.writeTo(new FileOutputStream(pdfFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
    }
}

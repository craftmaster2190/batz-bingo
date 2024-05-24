package com.craftmaster2190.bingo.batzbingo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import static com.craftmaster2190.bingo.batzbingo.BingoCard.MIDDLE;

@Service
public class BingoPdfGenerator {

  public void printBingoCard(BingoCard bingoCard, String filename, BaseColor backgroundColor) {
    try (var fileOutputStream = new FileOutputStream(filename)) {
      Document document = new Document();
      var writer = PdfWriter.getInstance(document, fileOutputStream);

      document.open();
      PdfPTable headerTable = new PdfPTable(1);
      String fontFamily = FontFactory.HELVETICA;
      Font fontCell = FontFactory.getFont(fontFamily, 60, BaseColor.BLACK);
      Font fontTitle = FontFactory.getFont(fontFamily, 55, BaseColor.BLACK);
      Font fontFree = FontFactory.getFont(fontFamily, 25, BaseColor.BLACK);
      var headerCell = new PdfPCell(new Phrase("coltonparty.com", fontTitle));
      headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      headerCell.setBorderWidth(0);
      headerCell.setPaddingBottom(60);
      headerTable.addCell(headerCell);
      document.add(headerTable);

      var cellHeight = 80;
      PdfPTable table = new PdfPTable(5);
      Arrays.stream(BingoLetter.values()).forEach(letter -> {
        PdfPCell cell = new PdfPCell(new Phrase(letter.name(), fontCell));
        cell.setBackgroundColor(backgroundColor);
        cell.setBorderWidth(2);
        cell.setFixedHeight(cellHeight);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
      });

      for (int i = 0; i <= 4; i++) {
        for (BingoLetter letter : BingoLetter.values()) {
          int cellValue = bingoCard.getColumn(letter)[i];
          String cellValueString;
          var cellFont = fontCell;
          if (cellValue == MIDDLE) {
            cellValueString = "FREE";
            cellFont = fontFree;
          }
          else {
            cellValueString = String.valueOf(cellValue);
          }
          PdfPCell cell = new PdfPCell(new Phrase(cellValueString, cellFont));
          cell.setBorderWidth(2);
          cell.setFixedHeight(cellHeight);
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cell);
        }
      }
      document.add(table);

      document.close();
      writer.close();
    } catch (IOException | DocumentException e) {
      throw new RuntimeException("Unable to generate PDF for \n" + bingoCard, e);
    }
  }

}

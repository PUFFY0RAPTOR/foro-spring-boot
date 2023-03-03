package com.foro.juniors.foro.util;

import com.foro.juniors.foro.Models.Entity.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("usuarios/listarUsuario")
public class ListarPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
    HttpServletRequest request, HttpServletResponse response) throws Exception {

         List<Usuario> listUsuarios = (List<Usuario>) model.get("datos");

         PdfPTable tablaUsers = new PdfPTable(6);

         listUsuarios.forEach(user ->{
             tablaUsers.addCell(user.getId().toString());
             tablaUsers.addCell(user.getNombre());
             tablaUsers.addCell(user.getApellido());
             tablaUsers.addCell(user.getTel().toString());
             tablaUsers.addCell(user.getNombreUsuario());
             tablaUsers.addCell(user.getContrasena());
         });

         document.add(tablaUsers);
    }
}

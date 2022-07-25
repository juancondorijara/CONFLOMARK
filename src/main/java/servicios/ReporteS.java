package servicios;

import dao.Conexion;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReporteS extends Conexion {

    public void exportarPDFGlobal(Map parameters, String url, String nomPDF) throws JRException, IOException, Exception {
        this.conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/" + url + ""));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.getCn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=" + nomPDF + "");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

}

package api.ositel;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import api.ositel.classe.Result;
import api.ositel.service.FileService;
import api.ositel.utils.ConstantsManager;
import api.ositel.utils.EnumNomMethodes;



@Path("api/ositel")
public class OsitelExcelFileWebService {
	
	
	/** Méthode 1 
	 *  http://localhost:8080/api/ositel/addExcelFile 
     *  Prend en input Le nom du fichier Excel */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("addExcelFile")
	public Result addExcelFile(JSONObject jSONObject) {
		Result res = null;
		
		if(jSONObject != null) {
			
			try {
				
				// Get Name File
				String fileName = jSONObject.get("fileName").toString();
				
				// Create New File Excel
				FileService.AddNewFileByName(fileName);
				
				// return Message Accuse
				res = FileService.getResultNewFile(fileName);
				
				ConstantsManager.NOM_ANC_METHODE = EnumNomMethodes.addExcelFile;
				res.setMessage(ConstantsManager.MESSAGE_ACCUSE);
				
			} catch (Exception e) {
				
				res.setMessage(ConstantsManager.MESSAGE_FAILD);
				res.setMessageErreurDetail(e.getMessage());
			}
			
		}
		return  res ;
	}

	/** Méthode 2
	 * 	http://localhost:8080/api/ositel/{idExcelFile}/uploadExcelFile
	 *  Prend en input le fichier Excel à Uploader (Binaire) */
	@POST
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{idExcelFile}/uploadExcelFile")
	public String uploadExcelFile(@PathParam("idExcelFile") Long idExcelFile, InputStream file) {
		
		try {
			FileService.UpDateBinaryFileById(idExcelFile,file);
			ConstantsManager.NOM_ANC_METHODE = EnumNomMethodes.uploadExcelFile;
			return ConstantsManager.MESSAGE_ACCUSE;
			
		} catch (Exception e) {
			
			return ConstantsManager.MESSAGE_FAILD +" : " + e.getMessage();
			
		}
		
	}
	
	/** Méthode 3
	 *	http://localhost:8080/api/ositel/searchExcelFile
	 *	Prend en input le nom du fichier à chercher et retourne sa structure sous format JSON */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("searchExcelFile")
	public Result searchExcelFile(JSONObject jSONObject) {

		Result res = null;
		try {
			// Get Name File
			String fileName = jSONObject.get("fileName").toString();
			
			// return structure sous format JSON
			res = FileService.getResultByNameFile(fileName);
			
			ConstantsManager.NOM_ANC_METHODE = EnumNomMethodes.searchExcelFile;
			ConstantsManager.NOM_FILE = fileName;
			res.setMessage(ConstantsManager.MESSAGE_ACCUSE);
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(ConstantsManager.MESSAGE_FAILD);
		}
		
	return  res ;
	}
	
	/** Méthode 4
	 * http://localhost:8080/api/ositel/{colonne}/{line}/updateCellValue
	 * Pour modifier la valeur d’une cellule. */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{colonne}/{line}/updateCellValue")
	public String updateCellValue(@PathParam("colonne") int colonne,@PathParam("line") int line, JSONObject jSONObject) {
		String valeur = "";
		// Get Name File
		try {
			valeur = jSONObject.get("value").toString();
			FileService.UpDateValueFileExcel(line , colonne , valeur );
			return ConstantsManager.MESSAGE_ACCUSE;
			
		} catch (Exception e) {
			
			return ConstantsManager.MESSAGE_FAILD +" : " + e.getMessage();
		}
	}

}
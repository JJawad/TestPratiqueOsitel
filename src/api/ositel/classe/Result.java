package api.ositel.classe;

import java.util.List;

import api.ositel.utils.ConstantsManager;

public class Result {
	
	Long id;
	
	String fileName;
	
	String headerColumn;
	
	List<LigneValue> listlinesValue;
	
	String message;
	
	String messageErreurDetail;
	
	
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Result(String nameFile) {
		this.fileName = nameFile;
	}
	
	public Result(Long id ,String nameFile) {
		this.id = id;
		this.fileName = nameFile;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}




	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the headerColumn
	 */
	public String getHeaderColumn() {
		return headerColumn;
	}
	/**
	 * @param headerColumn the headerColumn to set
	 */
	public void setHeaderColumn(String headerColumn) {
		this.headerColumn = headerColumn;
	}

	/**
	 * @return the listlinesValue
	 */
	public List<LigneValue> getListlinesValue() {
		return listlinesValue;
	}

	/**
	 * @param listlinesValue the listlinesValue to set
	 */
	public void setListlinesValue(List<LigneValue> listlinesValue) {
		this.listlinesValue = listlinesValue;
	}
	
	

	/**
	 * @return the messageErreurDetail
	 */
	public String getMessageErreurDetail() {
		return messageErreurDetail;
	}

	/**
	 * @param messageErreurDetail the messageErreurDetail to set
	 */
	public void setMessageErreurDetail(String messageErreurDetail) {
		this.messageErreurDetail = messageErreurDetail;
	}

	@Override
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if(ConstantsManager.MESSAGE_FAILD.equals(this.message)) {
			sb.append("\"Message\" : \"" +this.message+"\"," );
			sb.append("\"messageErreurDetail\" : \"" +this.messageErreurDetail+"\"" );
		}else {
			if(this.id != null && this.id > 0) {
				sb.append("\"idExcelFile\":\""+this.id+"\",");
			}
			
			if(this.fileName != null && !"".equals(this.fileName)) {
				sb.append("\"fileName\":\""+this.fileName+"\"");
				if(this.headerColumn != null && !"".equals(this.headerColumn)) {
					sb.append(",");
					sb.append("\"headerColumn\":"+this.headerColumn);
					if(this.listlinesValue != null && this.listlinesValue.size() > 0 ) {
						sb.append(",");
						sb.append("\"linesValue\":{");
						Boolean isFirstLigne = true;
						for (LigneValue linesValue : listlinesValue) {
							
							if(isFirstLigne) {
								isFirstLigne = false;
							}else {
								sb.append(",");
							}
							sb.append(linesValue.toString());
						}
						sb.append("}");
					}
				} 
			}
			 
			
		}
		sb.append("}");
		return sb.toString();
	}

}

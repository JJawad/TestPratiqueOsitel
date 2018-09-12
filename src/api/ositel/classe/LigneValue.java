package api.ositel.classe;

import java.util.List;

public class LigneValue {

	int id;
	List<String> valeurs;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the valeurs
	 */
	public List<String> getValeurs() {
		return valeurs;
	}
	/**
	 * @param valeurs the valeurs to set
	 */
	public void setValeurs(List<String> valeurs) {
		this.valeurs = valeurs;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\""+this.id + "\":[");
		String sbValeur="";
		for (String valeur : valeurs) {
			if(!"".equals(sbValeur)) {
				sbValeur += ",";
			}
			sbValeur += "\""+valeur+"\"";
		}
		sb.append(sbValeur);
		sb.append("]");
		return sb.toString();
	}
	
}

package es_uclm_esi_isoft2_restaurante_pedidoComandas_persistencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import es_uclm_esi_isoft2_restaurante_pedidoComandas_dominio.*;

public class IngredienteDAO {

	/**
	 * 
	 * @param ingrediente
	 * @param idRestaurante
	 */
	public void insertIngrediente(Ingrediente ingrediente, int idRestaurante) {
		// TODO - implement IngredienteDAO.insertIngrediente
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idIngrediente
	 * @param idRestaurante
	 */
	public void deleteIngrediente(int idIngrediente, int idRestaurante) {
		// TODO - implement IngredienteDAO.deleteIngrediente
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idIngrediente
	 * @param idRestaurante
	 */
	public void updateIngrediente(int idIngrediente, int idRestaurante) {
		// TODO - implement IngredienteDAO.updateIngrediente
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idIngrediente
	 * @param idRestaurante
	 */
	public static Ingrediente getIngrediente(int idIngrediente, int idRestaurante) throws IOException {
		URL obj = new URL("https://isoft2-2021-b03.000webhostapp.com/phpGetIngrediente.php");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setDoOutput(true);

		OutputStream directConnection = con.getOutputStream();

		directConnection.write(("IdRestaurante=" + idRestaurante + "&Id=" + idIngrediente).getBytes());
		directConnection.flush();
		directConnection.close();

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			//System.out.println(response.toString());

					StringTokenizer st = new StringTokenizer(response.toString(), "]");

			if (!response.toString().equals("[]"))
				return stringToIngrediente(st.nextToken().replace("[", "").replace(",", " "), false);
			return null;
		} else {
			System.out.println("NO FUNCIONÃ“");
			return null;
		}
	}
	
	/*************************************************************************************
	 * Con esta consulta
	 * 
	 * @date 11/12/2020
	 * @author Javier Ã�lvarez
	 *************************************************************************************/
	public static ArrayList<Ingrediente> getIngredientes(int id_Plato) throws IOException {
		URL obj = new URL("https://isoft2-2021-b03.000webhostapp.com/phpGetIngredientes.php");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setDoOutput(true);

		OutputStream directConnection = con.getOutputStream();

		directConnection.write(("Id_plato=" + id_Plato).getBytes());
		directConnection.flush();
		directConnection.close();

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

			ArrayList<Ingrediente> array = new ArrayList<Ingrediente>();
			StringTokenizer st = new StringTokenizer(response.toString(), "]");

			if (!response.toString().equals("[]"))
				while (st.hasMoreTokens()) {
					array.add(stringToIngrediente(st.nextToken().replace("[", "").replace(",", " "), true));
				}
			/*for (Ingrediente f : array)
				System.out.println(f.toString());*/
			
			return array;
		} else {
			System.out.println("NO FUNCIONÃ“");
			return null;
		}
	}

	public static Ingrediente stringToIngrediente(String r, boolean fromAlimento) {
		StringTokenizer st = new StringTokenizer(r, " ");
		Ingrediente ingrediente = null;

		try {
			
			if(fromAlimento) {
				int idPlato = Integer.parseInt(st.nextToken().replace("\"", ""));
				int idIngrediente = Integer.parseInt(st.nextToken().replace("\"", ""));
				float cantidadIngrediente = Float.parseFloat(st.nextToken().replace("\"", ""));
				st.nextToken().replace("\"", "");
				String nombre = st.nextToken().replace("\"", "");

				ingrediente = new Ingrediente(idIngrediente, nombre, cantidadIngrediente);
			}else {
				
				int idIngrediente = Integer.parseInt(st.nextToken().replace("\"", ""));
				float cantidadIngrediente = Float.parseFloat(st.nextToken().replace("\"", ""));
	
				ingrediente = new Ingrediente(idIngrediente, cantidadIngrediente);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingrediente;
	}
	

}
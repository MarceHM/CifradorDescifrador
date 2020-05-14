package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Cifrador {

	/**
	 * Constante con el algoritmo para el SecretKeySpec AES
	 */

	public final static String ALGORITMO = "AES";

	/**
	 * Constante con el algoritmo para el Cipher AES/ECB/NoPadding
	 */

	public final static String ALGORITMO_2 = "AES/ECB/NoPadding";

	/**
	 * Variable tipo String para la contraseña o clave
	 */

	private String contrasenha;

	/**
	 * Variable de tipo File con el archivo que será cifrado
	 */
	private File archivo;
	
	private byte[] sha;

	/**
	 * Constructor de la clase Cifrador
	 * 
	 * @param archivo archivo a cifrar
	 * @param key     contraseña a asignar
	 */

	public Cifrador(File archivo, String key) {

		this.contrasenha = key;
		this.archivo = archivo;

	}

	/**
	 * Constructor Cifrador
	 */
	public Cifrador() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Método getContrasenha
	 * 
	 * @return contraseña
	 */
	public String getContrasenha() {
		return contrasenha;
	}

	/**
	 * Método setContrasenha Modifica la contraseña
	 * 
	 * @param contrasenha
	 */
	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	/**
	 * Método getArchivo
	 * 
	 * @return archivo
	 */
	public File getArchivo() {
		return archivo;
	}

	/**
	 * Método setArchivo Modifica el archivo
	 * 
	 * @param archivo
	 */
	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	/**
	 * Método cifrar
	 * 
	 * @param file archivo a cifrar
	 * @param key  contraseña
	 * @return newFile archivo con contenido cifrado
	 */
	public File cifrar(File file, String key) {

		byte[] theKey = null;
		File newFile = null;
		try {
			String clave = generateStorngPasswordHash(key);
			theKey = hexToBytes(clave);
			// KeySpec ks = new DESKeySpec(theKey);
			SecretKeySpec ks = new SecretKeySpec(theKey, ALGORITMO);
			// SecretKeyFactory kf = SecretKeyFactory.getInstance("AES");
			// SecretKey ky = kf.generateSecret(ks);
			Cipher cf = Cipher.getInstance(ALGORITMO_2);
			cf.init(Cipher.ENCRYPT_MODE, ks);
			byte[] cif = fileToByte(file);
			/*
			 * for (int i = 0; i < cif.length; i++) { System.out.println(cif[i]); }
			 * System.out.println("largo"+cif.length); String archivo1=toHex(cif); byte[]
			 * conver=hexToBytes(cif);
			 */
			byte[] theCph = cf.doFinal(cif);
			newFile = byteToFile(theCph);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newFile;
	}

	/**
	 * Método fileToByte Convierte un archivo a un arreglo de bytes
	 * 
	 * @param file archivo para conversión
	 * @return bytes arreglo de tipo byte
	 */

	public byte[] fileToByte(File file) {

		byte[] bytes = null;
	

		try {
			
			MessageDigest messageDigest= MessageDigest.getInstance("SHA");
			// bytes = Files.readAllBytes(file.toPath());
			FileInputStream fis = new FileInputStream(file);
			int mod = (int) file.length() % 16;
			if (mod == 0) {
				bytes = new byte[(int) file.length()];
			} else {
				bytes = new byte[(int) file.length() + (16 - mod)];
			}

			fis.read(bytes);
			byte[] holi=bytes;
			messageDigest.update(holi);
			
			fis.close();
			sha=messageDigest.digest();
			System.out.println(bytesToHex(sha));

		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return bytes;
	}

	/**
	 * Método byteToFile Convierte un arreglo de bytes a un archivo
	 * 
	 * @param bytes arreglo de bytes para conversión
	 * @return file archivo
	 */
	public File byteToFile(byte[] bytes) {
		File file = new File("C:\\Users\\Alejandra Sanchez\\Desktop\\salida.txt");

		try {
			OutputStream os = new FileOutputStream(file);
			os.write(bytes);
			os.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	/**
	 * Método hexToBytes Convierte de hexadecimal a un arreglo de bytes
	 * 
	 * @param str Cadena de hexadecimales
	 * @return buffer arreglo de tipo byte
	 */
	public static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}

	}

	/**
	 * Método bytesToHex Convierte un arreglo de bytes a una cadena de hexadecimales
	 * 
	 * @param data arreglo de bytes para conversión
	 * @return str cadena de hexadecimales
	 */
	public static String bytesToHex(byte[] data) {
		if (data == null) {
			return null;
		} else {
			int len = data.length;
			String str = "";
			for (int i = 0; i < len; i++) {
				if ((data[i] & 0xFF) < 16)
					str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
				else
					str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
			}
			return str.toUpperCase();
		}
	}

	/**
	 * Método generateStorngPasswordHash
	 * Genera la clave haciendo uso del algoritmo PBKDF2
	 * Créditos: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	 * Algunas modificaciones propias de los autores de este proyecto
	 * @param password contraseña 
	 * @return hash cadena de hexadecimales que son la clave generada
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static String generateStorngPasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 128);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		System.out.println(toHex(hash));
		return toHex(hash);
	}
	
	
	/**
	 * Método getSalt
	 * A través de un secure random genera un salt de length 16
	 * @return salt arreglo de bytes
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	
	/**
	 * Método toHex
	 * Convierte un arreglo de bytes en una cadena de hexadecimales 
	 * @param array arreglo de bytes para conversión
	 * @return hex cadena de hexadecimales 
	 * @throws NoSuchAlgorithmException
	 */
	public static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}
}

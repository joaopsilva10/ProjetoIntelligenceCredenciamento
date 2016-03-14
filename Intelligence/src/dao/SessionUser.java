package dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionUser {
	
	SharedPreferences preferencesSessionUser;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	
	private static final String PREF_NAME = "SESSION_INTELLIGENCE_USER";
	private static final String IS_LOGIN = "IsLoggedIn";
	private static final String LOGIN_USER = "Login_user";
	private static final String SENHA_USER = "senha_user";
	
	//contrutor da sessão
	public SessionUser(Context context){
		
		this._context = context;
		preferencesSessionUser = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = preferencesSessionUser.edit();
	}
	
	//contruindo a sessão
	public void criaSessionLogin(String login, String senha){
		
		//passando o login como verdadeiro
		editor.putBoolean(IS_LOGIN, true);
		//pegando o uer
		editor.putString(login, LOGIN_USER);
		//pegando a senha
		editor.putString(senha, SENHA_USER);
		
		editor.commit();
	}
	
}


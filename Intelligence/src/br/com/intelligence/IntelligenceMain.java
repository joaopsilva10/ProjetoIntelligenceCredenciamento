package br.com.intelligence;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.history.HistoryActivity;

public class IntelligenceMain extends Activity {

	TextView user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intelligence);
		user = (TextView) findViewById(R.id.txtUser);

		String logado;

		Intent intent = getIntent();

		Bundle dados = intent.getExtras();

		logado = dados.getString("login").toString();

		user.setText("Olá, " + logado);

	}

	public void digitalizar(View view) {
		Intent intent = new Intent(this,
				br.com.intelligence.TelaDeAtividades.class);
		startActivity(intent);
		// finish();
	}

	public void historicoQr(View view) {
		Intent passaTela = new Intent(this, HistoryActivity.class);
		startActivity(passaTela);

	}

	// metodo sair...
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// desabilitando o bobão de voltar
	}

	// criando menu de opções

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		super.onCreateOptionsMenu(menu);
		
		getMenuInflater().inflate(R.menu.menu_intelligence_op, menu);
		
		return true;
	}

	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.menu_intelligence_sobre:
//			mandar pra tela de sobre o app
			break;

		case R.id.menu_intelligence_sair:
//			sair da apicação, fechar session do user que está logado
			msgAlerta();
			break;
		}
		
		return super.onMenuItemSelected(featureId, item);
	}
	
	private void msgAlerta() {
		// criando uma caixa de confirmação usando AlertDialog
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		// definindo o titulo
		alerta.setTitle("Atenção!");
		// difinindo a msg
		alerta.setMessage("Deseja realmente sair?");
		// se clicar em Sim
		alerta.setPositiveButton("Sair", new DialogInterface.OnClickListener() {
			// metodo verifica condição sai da aplicação
			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub
				msgSair();
				finish();
			}
		});
		// se clicar em não
		alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			// metodo verifica condição e volta para a aplicaÃ§Ã£o
			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		// cria o AlertDialog
		alerta.create();
		// exibi o AlertDialog
		alerta.show();
	}
	
	public void msgSair(){
		Toast.makeText(this, "Você saiu do Intelligence",
				Toast.LENGTH_LONG).show();
	}
}

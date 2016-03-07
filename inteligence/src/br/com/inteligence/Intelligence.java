package br.com.inteligence;

import android.app.Activity;
import android.app.LauncherActivity.IconResizer;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.client.android.history.HistoryActivity;

public class Intelligence extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intelligence);
	}

	public void digitalizar(View view) {
		Intent intent = new Intent(this,
				br.com.inteligence.TelaDeAtividades.class);
		startActivity(intent);
		finish();
	}

	public void historicoQr(View view) {
		Toast.makeText(this, "Você selecionou a opção de histórico",
				Toast.LENGTH_SHORT).show();
		Intent passaTela = new Intent(this, HistoryActivity.class);
		startActivity(passaTela);
		finish();

	}

	// metodo sair...
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finishActivity(0);
	}
}
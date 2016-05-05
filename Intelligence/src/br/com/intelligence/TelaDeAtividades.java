package br.com.intelligence;


import generic.ScreenResolution;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import dao.AtividadesDAO;
import dao.EventosDAO;

public class TelaDeAtividades extends Activity implements ScreenResolution{

	private String dados_recolhidos;
	public static final int REQUEST_CODE = 0;
	Intent intent;
	Spinner spnEventos, spnAtividades;
	private EventosDAO eventosDAO;
	private AtividadesDAO atvDAO;
	public static final String  PREF_NAME = "PreferenciasLogin";
	private ArrayAdapter<String> adptSpinnerAtv;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_de_atividades);
		pegaScreenResolution();

		eventosDAO = new EventosDAO(getApplicationContext());
		atvDAO = new AtividadesDAO(getApplicationContext());
		// criando spiner
		//tratando o spinner de Eventos
		spnEventos = (Spinner) findViewById(R.id.spn_eventos);
		spnAtividades = (Spinner) findViewById(R.id.spn_atividades);

		ArrayAdapter<String> adptSpinnerEv = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, eventosDAO.listarNomesEventos());

		adptSpinnerEv.setDropDownViewResource(android.R.layout.simple_list_item_1);
		spnEventos.setAdapter(adptSpinnerEv);
		// pegando o evento selecionado
		 String nomeEvento = spnEventos.getSelectedItem().toString();
		 
		 //tratando o spinner de Atividades
		 adptSpinnerAtv = new ArrayAdapter<String>(this, 
				 android.R.layout.simple_list_item_1, atvDAO.listarNomesAtividades(nomeEvento.toString()));
		 adptSpinnerAtv.setDropDownViewResource(android.R.layout.simple_list_item_1);
		 spnAtividades.setAdapter(adptSpinnerAtv);
		 
		 spnEventos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(spnEventos.getCount() > 0){
					 String nomeEvento = spnEventos.getSelectedItem().toString();
					 adptSpinnerAtv.clear();
					 adptSpinnerAtv.addAll(atvDAO.listarNomesAtividades(nomeEvento.toString()));
					 adptSpinnerAtv.notifyDataSetChanged();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
	}

	
	@Override
	public void pegaScreenResolution() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int y = metrics.heightPixels;

		int x = metrics.widthPixels;
		Log.i("Script", " " + y + " por " + x);
		if(y <= 1000 && x <= 550){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			
		}else{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
		}

	}


	// tratando o bot�o iniciar
	public void iniciar(View view) {
		intent = new Intent(this,
				com.google.zxing.client.android.CaptureActivity.class);
		
		//salvando dados na session do logado
		SharedPreferences preferenciasUser = getSharedPreferences(
				PREF_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferenciasUser
				.edit();
		
		//pegando o evento a atividade que foi selecionada e salvando na session do logado
		editor.putString("Evento", spnEventos.getSelectedItem().toString());
		if(spnAtividades.getCount() == 0){
			editor.putString("Atividade", "");
			editor.commit();
		}
		startActivityForResult(intent, REQUEST_CODE);
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {

			Bundle extras = data.getExtras();
			if (extras != null) {
				dados_recolhidos = "Resultado do SCANER: "
						+ extras.getString("SCAN_RESULT") + "( "
						+ extras.getString("SCAN_FORMAT") + ")";
				Log.i("Script", dados_recolhidos);
			}

		}

	}

	// saindo
	public void onBackPressed() {
		super.onBackPressed();
		this.finishActivity(0);
	}
}
package Main;

import java.util.Date;

import mesures.Data;
import mesures.Location;
import mesures.Map;
import mesures.Measures;
import mesures.MesuresFactory;
import mesures.MetaData;
import mesures.PointOnEarth;
import mesures.Time;
import mesures.Value;

import org.eclipse.emf.ecore.resource.Resource;

import Controller.ExtractLoad;

import timer.Timer;

public class Inserer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MesuresFactory factory = MesuresFactory.eINSTANCE;
		Time t;
		Value v;
		final int ANNEE_MIN = 1900, ANNEE_MAX = 1900, HEURE = 16, MINUTES = 00;
		// String[] villes = {"Brest", "Tanger"};
		Float[][] T = { 
				{ (float) 12.5, (float) 16.2 },
				{ (float) 13.1, (float) 16.8 }, 
				{ (float) 14.0, (float) 17.9 },
				{ (float) 15.2, (float) 19.2 }, 
				{ (float) 17.7, (float) 21.9 },
				{ (float) 20.6, (float) 24.9 }, 
				{ (float) 23.5, (float) 28.3 },
				{ (float) 23.9, (float) 28.6 }, 
				{ (float) 22.8, (float) 27.3 },
				{ (float) 19.7, (float) 23.7 }, 
				{ (float) 15.9, (float) 19.6 },
				{ (float) 13.3, (float) 17.0 }};
		
		ExtractLoad el = new ExtractLoad();
		
		Timer timer = new Timer(); 
		timer.start();
		
		Resource res = el.chargerModele("uri.mesures");
		
		Measures mesures = (Measures) res.getContents().get(0);
		Map theMap = mesures.getTheMap();
		
		PointOnEarth tanger = factory.createPointOnEarth();
		tanger.setLatitude(35.736097);
		tanger.setLongitude(-5.894165);
		tanger.setName("FST-Tanger");

		Location altitude_tanger = factory.createLocation();
		altitude_tanger.setAltitude(29);

		tanger.getTheDepths().add(altitude_tanger);
		altitude_tanger.setThePoint(tanger);
		theMap.getThePoints().add(tanger);

		Data data = factory.createData();
		data.setName("Températures moyennes");
		data.setDataType("Numerique");

		MetaData temperature = factory.createMetaData();
		temperature.setStandardName("Température");
		temperature.setUnit("°C");

		data.setTheMetadata(temperature);

		mesures.getTheData().add(data);

		for (int annee = ANNEE_MIN; annee <= ANNEE_MAX; annee++) {
			Boolean bissextile = false;
			if (annee % 400 == 0 || (annee % 4 == 0 && annee % 100 != 0))
				bissextile = true;
			for (int mois = 1; mois <= 12; mois++) {
				int maxJours = 30;
				if (mois == 2)
					maxJours = bissextile ? 29 : 28;
				else if (mois == 4 || mois == 6 || mois == 9 || mois == 11)
					maxJours = 30;
				else if (mois == 1 || mois == 3 || mois == 5 || mois == 7
						|| mois == 8 || mois == 10 || mois == 12)
					maxJours = 31;
				for (int JOUR = 1; JOUR <= maxJours; JOUR++) {
					t = factory.createTime();
					t.setLaDate(new Date(annee, mois - 1, JOUR, HEURE, MINUTES));

					mesures.getTheTimes().add(t);

					v = factory.createValue();
					double tempo = (T[mois - 1][0] + (float) Math.random()
							* (T[mois - 1][1] - T[mois - 1][0])) * 10;
					int valeur_entier = (int) (tempo);
					tempo = (double) valeur_entier / 10;
					v.setValue(tempo);

					data.getTheValues().add(v);

					t.getTheValues().add(v);
					v.setTheTime(t);

					altitude_tanger.getTheValues().add(v);
					v.setTheLocation(altitude_tanger);

					el.sauverModele("uri.mesures", mesures);

				}
			}
		}
		
		timer.stop();
		System.out.println("Chargement et insértion de données terminée en: "+ timer.getExecutionTime());
		System.out.println(timer.getTemps_dexec_ns() + " ns");
		
		el.sauverModele("uri.mesures", mesures);
	}

}

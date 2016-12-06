package Main;

import java.util.Date;

import mesures.Data;
import mesures.Location;
import mesures.Map;
import mesures.Measures;
import mesures.MesuresFactory;
import mesures.MesuresPackage;
import mesures.MetaData;
import mesures.PointOnEarth;
import mesures.Time;
import mesures.Value;

import Controller.ExtractLoad;

import timer.Timer;

public class Generer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

// ***************************************************Declarations*************************************************//

		MesuresFactory factory = MesuresFactory.eINSTANCE;
		ExtractLoad el = new ExtractLoad();
		Time t;
		Value v;
		final int ANNEE_MIN = 1900, ANNEE_MAX = 1900, HEURE = 16, MINUTES = 00;
		// String[] villes = {"Brest", "Tanger"};
		Float[][] T = { { (float) 9.6, (float) 10.9 },
				{ (float) 8.7, (float) 10.6 }, { (float) 9.9, (float) 12.0 },
				{ (float) 12.4, (float) 16.4 }, { (float) 14.5, (float) 16.7 },
				{ (float) 17.2, (float) 20.6 }, { (float) 20.7, (float) 22.6 },
				{ (float) 19.8, (float) 21.7 }, { (float) 18.4, (float) 22.8 },
				{ (float) 16.2, (float) 18.1 }, { (float) 11.7, (float) 14.6 },
				{ (float) 10.7, (float) 14.1 } };

// ***************************************************Fin Declarations*************************************************//

//***************************************************Generateur*************************************************//

		Timer timer = new Timer();
		timer.start();
		Measures mesures = factory.createMeasures();
		mesures.setName("Température à Brest");

		Map theMap = factory.createMap();
		mesures.setTheMap(theMap);

		PointOnEarth mairie_de_Brest = factory.createPointOnEarth();
		mairie_de_Brest.setLatitude(48.39119189771081);
		mairie_de_Brest.setLongitude(-4.485018253326416);
		mairie_de_Brest.setName("Brest");

		Location altitude_mairie_de_brest = factory.createLocation();
		altitude_mairie_de_brest.setAltitude(61.0);

		mairie_de_Brest.getTheDepths().add(altitude_mairie_de_brest);
		theMap.getThePoints().add(mairie_de_Brest);

		Data data = factory.createData();
		data.setName("Températures maximales moyennes");
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

					altitude_mairie_de_brest.getTheValues().add(v);

					el.sauverModele("uri.mesures", mesures);

				}
			}
		}
		timer.stop();
		System.out.println("Generation et sauvegarde de données terminée en: "
				+ timer.getExecutionTime());
		System.out.println(timer.getTemps_dexec_ns() + " ns");
		
		el.sauverModele("uri.mesures", mesures);

	}
}

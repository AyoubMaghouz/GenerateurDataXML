package Main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mesures.Location;
import mesures.Measures;
import mesures.MesuresFactory;
import mesures.PointOnEarth;
import mesures.Time;
import mesures.Value;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;

import timer.Timer;

import Controller.ExtractLoad;

public class Chercher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MesuresFactory factory = MesuresFactory.eINSTANCE;
		Timer timer = new Timer();
		
		ExtractLoad el = new ExtractLoad();
		
		timer.start();
		
		Resource res = el.chargerModele("uri.mesures");
		
		Measures mesures = (Measures) res.getContents().get(0);
		
		
		PointOnEarth p = factory.createPointOnEarth();
		p.setLatitude(48.39119189771081);
		p.setLongitude(-4.485018253326416);
		p.setName("Brest");
		
		Location loc = factory.createLocation();
		loc.setAltitude(61.0);
		p.getTheDepths().add(loc);
		
		
		Date d = new Date(1900, 0, 1, 16, 0);
		
		System.out.println(getTemperature(mesures, p, d));
		
		Map<Location, Double> m = getTemperatureMoyenne(mesures, p); 
		for (Location location : m.keySet()) {
			System.out.println(location.getThePoint().getName() + "//---/---//"+m.get(location));
		}
		System.out.println();
		
		timer.stop();
		
		System.out.println("Chargement et requete simple terminée en: "+ timer.getExecutionTime());
		System.out.println(timer.getTemps_dexec_ns() + " ns");
	}
	
	public static double getTemperature(Measures m, PointOnEarth p, Date d) {
		double res = -1;
		for (Time t : (EList<Time>)(m.getTheTimes())) {
			if(t.getLaDate().getTime() == d.getTime()){
//				System.out.println(t.getLaDate().getTime()+"//---//---//"+d.getTime());
				for (Value v : (EList<Value>)t.getTheValues()) {
//					System.out.println(v.getValue());
					for (Location l : (EList<Location>)p.getTheDepths()) {
//						System.out.println(l.getAltitude()+"//----//"+v.getTheLocation().getAltitude());
//						System.out.println(l.getThePoint().getLatitude()+"//----//"+v.getTheLocation().getThePoint().getLatitude());
//						System.out.println(l.getThePoint().getLongitude()+"//----//"+v.getTheLocation().getThePoint().getLongitude());
//						System.out.println(l.getThePoint().getName()+"//----//"+v.getTheLocation().getThePoint().getName());

						if(l.getAltitude() == v.getTheLocation().getAltitude() 
								&& l.getThePoint().getLatitude() == v.getTheLocation().getThePoint().getLatitude()
								&& l.getThePoint().getLongitude() == v.getTheLocation().getThePoint().getLongitude()
								&& l.getThePoint().getName().equals(v.getTheLocation().getThePoint().getName()))
						{
							res =  v.getValue();
						}
					}
				}
			}
		}
		return res;
	}

	public static Map<Location, Double> getTemperatureMoyenne(Measures m, PointOnEarth p) {
		Map<Location, Double> map = new HashMap<Location, Double>();
		for (Location location : (EList<Location>)p.getTheDepths()) {
			double moy = 0;
			for (Value value : (EList<Value>) location.getTheValues()) {
				moy += value.getValue();
			}
			map.put(location, moy/location.getTheValues().size());
		}
		return map;
	}
}

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

	public static void main(String[] args) {

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
		loc.setThePoint(p);
		
		Date d = new Date(1900, 0, 1, 16, 0);
		
		System.out.println(getTemperature(mesures, p, d));
		
		Map<String, Double> m = getTemperatureMoyenne(mesures, p); 
		for (String location : m.keySet()) {
			System.out.println(location+m.get(location));
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
				for (Value v : (EList<Value>)t.getTheValues()) {
					for (Location l : (EList<Location>)p.getTheDepths()) {
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

	public static Map<String, Double> getTemperatureMoyenne(Measures m, PointOnEarth p) {
		Map<String, Double> map = new HashMap<String, Double>();
		PointOnEarth myPointOnEarth = null;
		for (PointOnEarth pp : (EList<PointOnEarth>)m.getTheMap().getThePoints()) {
			if(pp.getLatitude() == p.getLatitude() && pp.getLongitude() == p.getLongitude() && pp.getName().equals(p.getName())){
				myPointOnEarth = pp;
				continue;
			}
		}
		for (Location location : (EList<Location>)myPointOnEarth.getTheDepths()) {
			double moy = 0;
			for (Value value : (EList<Value>) location.getTheValues()) {
				moy += value.getValue();
			}
			map.put(""+myPointOnEarth.getName()+" - "+location.getAltitude()+" m -> ", moy/location.getTheValues().size());
		}
		return map;
	}
}

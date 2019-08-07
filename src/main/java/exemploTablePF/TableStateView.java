package exemploTablePF;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dtTableStateView")
@SessionScoped
public class TableStateView implements Serializable {
     
    private List<Car> cars;
     
    private List<Car> filteredCars;
     
    private Car selectedCar;
     
    @ManagedProperty("#{carService}")
    private CarService service;
 
    @PostConstruct
    public void init() {
        cars = service.createCars(50);
    }
     
    public List<String> getBrands() {
        return service.getBrands();
    }
     
    public List<String> getColors() {
        return service.getColors();
    }
     
    public List<Car> getCars() {
        return cars;
    }
 
    public List<Car> getFilteredCars() {
        return filteredCars;
    }
 
    public Car getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
 
    public void setFilteredCars(List<Car> filteredCars) {
        this.filteredCars = filteredCars;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
}
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Devices } from "./Devices";

@Injectable({
    providedIn:'root'
})

export class DevicesServic {
    private apiServerUrl='';
    constructor(private http:HttpClient) {
        
    }
    public getDevices():Observable<Devices[]>{
        return this.http.get<Devices[]>(`${this.apiServerUrl}/devices/all`);
    }
    public addDevices(device:Devices):Observable<Devices>{
        return this.http.get<Devices[]>(`${this.apiServerUrl}/devices/add`);
    }
}
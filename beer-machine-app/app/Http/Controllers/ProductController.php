<?php

namespace App\Http\Controllers;

use App\Http\Resources\TemperatureResource;
use App\Models\Batch;
use App\Models\Humidity;
use App\Models\Temperature;
use App\Models\Vibration;
use Illuminate\Http\Request;
use function PHPUnit\Framework\isNull;

class ProductController extends Controller
{

    public function index(){
        return view('home');
    }

    public function create(){
        return view('create');
    }
    public function store(Request $request){
        //validation
        $request->validate(['type'=>'required|between:0,5',
            'speed'=>'required|min:1|numeric|',
            'amount'=>'required|min:1|max:65535|numeric',
            'batch'=>'required|min:0|max:65535|numeric']);

        $batch = new Batch();
        $batch->batch = request('batch');
        $batch->type = request('type');
        $batch->amount = request('amount');
        $batch->speed = request('speed');
        $batch->producedAmount = 0;
        $batch->defectAmount=0;
        $batch->acceptedAmount=0;
        $batch->save();

        return redirect()->route('home');
    }
    // function that creates db entries depending on which type

    function updateData(){
        $type = \request('type');
        $value = \request('value');

        $batch = Batch::latest()->first();

        switch ($type){
            case "::Program:Cube.Admin.ProdDefectiveCount":
                $batch->defectAmount=$value;
                $batch->save();
                break;
            case "::Program:Cube.Admin.ProdProcessedCount":
                $batch->producedAmount = $value;
                $batch->acceptedAmount = $batch->$value-$batch->defectAmount;
                $batch->save();
                break;
            case "::Program:Cube.Status.Parameter[3].Value":
                $temp = new Temperature();
                $temp->temperature = $value;
                $temp->batch_id = $batch->id;
                $temp->save();
                break;
            case "::Program:Cube.Status.Parameter[2].Value":
                $humidity = new Humidity();
                $humidity->humidity = $value;
                $humidity->batch_id = $batch->id;
                $humidity->save();
                break;
            case "::Program:Cube.Status.Parameter[4].Value":
                $vibration = new Vibration();
                $vibration->vibration = $value;
                $vibration->batch_id = $batch->id;
                $vibration->save();


        }
        return response()->json([\request()->all()]);
    }
    function getEverything(Batch $batch){

        $temp = Temperature::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $humidity = Humidity::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $vibration = Vibration::where('batch_id',$batch->id)->orderBy('id','DESC')->first();

        return response()->json(['batch'=>$batch,'temp'=>$temp,'humidity'=>$humidity,'vibration'=>$vibration]);
    }
    function getDashboard(){
        $batchId = Batch::all()->last();

        if(Temperature::where('batch_id', $batchId->id)->orderBy('id','DESC')->first() == null){ //Temperature and humidity is null if the simulation is used
            $avgTemp = "null";
        }
        else{
            $avgTemp = Temperature::all()->where('batch_id', $batchId->id)->avg('temperature');

        }

        if(Humidity::where('batch_id', $batchId->id)->orderBy('id','DESC')->first() == null){
            $avgHumidity = "null";
        }
        else{
            $avgHumidity = Humidity::all()->where('batch_id', $batchId->id)->avg('humidity');
        }

        return view('dashboard')->with('batch', $batchId)->with('temp', $avgTemp)->with('humidity', $avgHumidity);
    }
}

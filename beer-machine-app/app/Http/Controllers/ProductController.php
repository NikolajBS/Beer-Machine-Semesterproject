<?php

namespace App\Http\Controllers;


use App\Models\Batch;
use App\Models\Humidity;
use App\Models\Inventory;
use App\Models\Temperature;
use App\Models\Vibration;
use Illuminate\Http\Request;
use function PHPUnit\Framework\isNull;

class ProductController extends Controller
{

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

    function getEverything(){
        $batch = Batch::all()->last();
        $inventory = Inventory::first();
        $temp = Temperature::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $humidity = Humidity::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $vibration = Vibration::where('batch_id',$batch->id)->orderBy('id','DESC')->first();

        $data = ['wheat'=>$inventory->wheat,'barley'=>$inventory->barley,'yeast'=>$inventory->yeast,
            'malt'=>$inventory->malt,'hops'=>$inventory->hops,'maintenance'=>$inventory->maintenance];

        return response()->json(['batch'=>$batch,'temp'=>$temp,'humidity'=>$humidity,
            'vibration'=>$vibration,'inventory'=>$data]);
    }

    function getDashboard(){
        $batchId = Batch::all()->last();

        if (Temperature::where('batch_id', $batchId->id)->orderBy('id','DESC')->first() == null){ //Temperature and humidity is null when the simulation is used
            $avgTemp = "null";
        }
        else{
            $avgTemp = Temperature::all()->where('batch_id', $batchId->id)->avg('temperature');
        }

        if (Humidity::where('batch_id', $batchId->id)->orderBy('id','DESC')->first() == null){
            $avgHumidity = "null";
        }
        else{
            $avgHumidity = Humidity::all()->where('batch_id', $batchId->id)->avg('humidity');
        }

        return view('dashboard')->with('batch', $batchId)->with('temp', $avgTemp)->with('humidity', $avgHumidity);
    }
}

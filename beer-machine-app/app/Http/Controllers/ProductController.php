<?php

namespace App\Http\Controllers;

use App\Models\Batch;
use App\Models\Humidity;
use App\Models\State;
use App\Models\Temperature;
use App\Models\Vibration;
use Illuminate\Http\Request;

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
            'amount'=>'required|min:1|max:65535|numeric']);

        $batch = new Batch();
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
        $id = \request('id');
        $batch = Batch::find($id);
        switch ($type){
            case "producedAmount":
                $batch->producedAmount = $value;
                $batch->acceptedAmount = $batch->producedAmount-$batch->defectAmount;
                $batch->save();
                break;
            case "defective":
                $batch->defectAmount=$value;
                $batch->save();
                break;
            case "temperature":
                $temp = new Temperature();
                $temp->temperature = $value;
                $temp->batch_id = $id;
                $temp->save();
                break;
            case "humidity":
                $humidity = new Humidity();
                $humidity->humidity = $value;
                $humidity->batch_id = $id;
                $humidity->save();
                break;
            case "vibration":
                $vibration = new Vibration();
                $vibration->vibration = $value;
                $vibration->batch_id = $id;
                $vibration->save();
        }
    }
    function getEverything(Batch $batch){

        $temp = Temperature::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $humidity = Humidity::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $vibration = Vibration::where('batch_id',$batch->id)->orderBy('id','DESC')->first();

        return response()->json(['batch'=>$batch,'temp'=>$temp,'humidity'=>$humidity,'vibration'=>$vibration]);
    }
}

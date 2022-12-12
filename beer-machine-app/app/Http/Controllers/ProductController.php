<?php

namespace App\Http\Controllers;

use App\Http\Resources\BatchResource;
use App\Http\Resources\UserResource;
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

        switch ($type){
            case "producedAmount":
                //
            case "defective":
                //
            case "speed":
                //
            case "temperature":
                //
            case "humidity":
                //
            case "vibration":
                $vibration = new Vibration();
        }
    }
    function getEverything(Batch $batch){

        $temp = Temperature::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $humidity = Humidity::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $vibration = Vibration::where('batch_id',$batch->id)->orderBy('id','DESC')->first();

        return response()->json(['batch'=>$batch,'temp'=>$temp,'humidity'=>$humidity,'vibration'=>$vibration]);
    }
}

<?php

namespace App\Http\Controllers;


use App\Models\Batch;
use App\Models\Humidity;
use App\Models\Inventory;
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
            case "::Program:Cube.Admin.ProdProcessedCount":
                $batch->producedAmount = $value;
                $batch->acceptedAmount = $value-$batch->defectAmount;
                $batch->save();
                break;
            case "::Program:Cube.Admin.ProdDefectiveCount":
                $batch->defectAmount=$value;
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
                break;
            case "::Program:Inventory.Barley":
                $inv = Inventory::first();
                $inv->barley = $value;
                $inv->save();
                break;
            case "::Program:Inventory.Hops":
                $inv = Inventory::first();
                $inv->hops = $value;
                $inv->save();
                break;
            case "::Program:Inventory.Malt":
                $inv = Inventory::first();
                $inv->malt = $value;
                $inv->save();
                break;
            case "::Program:Inventory.Wheat":
                $inv = Inventory::first();
                $inv->wheat = $value;
                $inv->save();
                break;
            case "::Program:Inventory.Yeast":
                $inv = Inventory::first();
                $inv->yeast = $value;
                $inv->save();
                break;
            case "::Program:Maintenance.Counter":
                $inv = Inventory::first();
                $inv->maintenance = $value;
                $inv->save();
                break;
        }
        return response()->json([\request()->all()]);
    }
    function getEverything(Batch $batch){
        $inventory = Inventory::first();
        $temp = Temperature::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $humidity = Humidity::where('batch_id',$batch->id)->orderBy('id','DESC')->first();
        $vibration = Vibration::where('batch_id',$batch->id)->orderBy('id','DESC')->first();

        $data = ['wheat'=>$inventory->wheat,'barley'=>$inventory->barley,'yeast'=>$inventory->yeast,
            'malt'=>$inventory->malt,'hops'=>$inventory->hops,'maintenance'=>$inventory->maintenance];

        return response()->json(['batch'=>$batch,'temp'=>$temp,'humidity'=>$humidity,
            'vibration'=>$vibration,'inventory'=>$data]);
    }
//    function getInventory(){
//        $inventory = Inventory::first();
//        return response()->json([]);
//    }
}

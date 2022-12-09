<?php

namespace App\Http\Controllers;

use App\Http\Resources\BatchResource;
use App\Http\Resources\UserResource;
use App\Models\Batch;
use App\Models\Humidity;
use App\Models\State;
use App\Models\Temperature;
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
        $batch->save();
//        $request->session()->save();
        return redirect()->route('home');
    }

    function testing(){
//        $batch = new Batch();
//        $batch->amount =100;
//        $batch->type =0;
//        $batch->speed=300;
//        $batch->save();
//
//        // create humidity
//        $humidity = new Humidity();
//        $humidity->humidity = 33.2;
//        $humidity->batch_id = $batch->id;
//        $humidity->save();
//
//        //create temp
//        $state = new State();
//        $state->stoppingSeconds=100.3;
//        $state->startingSeconds=100.3;
//        $state->batch_id = $batch->id;
//        $state->save();

//        // create state
//        $temp = new Temperature();
//        $temp->temperature = 29.3;
//        $temp->batch_id = $batch->id;
//        $temp->save();

    }
}

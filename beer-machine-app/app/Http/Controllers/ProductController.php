<?php

namespace App\Http\Controllers;

use App\Http\Resources\UserResource;
use App\Models\Batch;
use Illuminate\Http\Request;

class ProductController extends Controller
{

    public function index(Batch $batch){
        return view('home',['batch'=>$batch]);
    }

    public function create(){
        return view('create');
    }
    public function store(Request $request){
        //validation
        $request->validate(['type'=>'required|between:0,5',
            'amount'=>'required|min:1|numeric|']);

        $batch = new Batch();
        $batch->type = request('type');
        $batch->amount = request('amount');
        $batch->save();

        return redirect()->route('home',['batch'=>$batch]);
    }
    public function retrieveData(){
        return new UserResource(Batch::latest()->first());
    }
}

<?php


use App\Http\Controllers\ProductController;
use App\Http\Resources\UserResource;
use App\Http\Controllers\Controller;

use App\Models\Batch;
use Illuminate\Support\Facades\Route;
use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| Web Routes//Route::get('elements',function (){
//    return Inventory::all();
//
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('home', function (){
    $batch = Batch::all()->last();
    return view('home',['batch'=>$batch]);
})->name('home');

Route::get('register', [ProductController::class, 'create'])->name('submit');
Route::post('register',[ProductController::class,'store'])->name('store');




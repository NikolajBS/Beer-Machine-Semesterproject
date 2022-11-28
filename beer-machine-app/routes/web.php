<?php

use App\Http\Controllers\Controller;
use App\Http\Controllers\BeerController;
use App\Models\Inventory;
use Illuminate\Support\Facades\Route;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});
Route::view('home','home');

Route::get('register', [Controller::class, 'changeEdit'])->name('change');

Route::post('home',[BeerController::class,'changeBatch'])->name('batch');

Route::get('elements',function (){
    return Inventory::all();
});
